/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamop.riche.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lamop.riche.dao.DAORelationWorkSourceIfs;
import org.lamop.riche.model.BibliographicType;
import org.lamop.riche.model.Person;
import org.lamop.riche.model.RelationSourcePerson;
import org.lamop.riche.model.RelationWorkSource;
import org.lamop.riche.model.Source;
import org.lamop.riche.model.Theme;
import org.lamop.riche.model.WorkAuthor;
import org.lamop.riche.model.WorkEntity;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clril
 */
public class ImportServiceImpl implements ImportServiceIfs {

//    private final String BDDUSER = "root";
//    private final String BDDPASS = "";
//    private final String BDDBASE = "riche_existant";
//    private final String BDDDRIVERJDBC = "riche_existant";
    private Connection connexion;
    private Connection connexion2;
    private Connection connexionRiche;

    protected BibliographicTypeServiceIfs serviceBibliographicType;

    private Map<String, BibliographicType> mapBibliographicType = new HashMap<>();
    private Map<Long, Theme> mapTheme = new HashMap<>();

    @Autowired
    private PersonServiceIfs servicePerson;

    @Autowired
    protected SourceServiceIfs serviceSource;

    @Autowired
    private ThemeServiceIfs themeService;

    @Autowired
    private WorkAuthorServiceIfs serviceWorkAuthor;

    @Autowired
    private WorkServiceIfs workService;

    @Autowired
    private DAORelationWorkSourceIfs daoRelationWorkSource;

    org.slf4j.Logger log = LoggerFactory.getLogger(ImportServiceImpl.class);

    private void initConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/riche_existant?allowMultiQueries=true", "root", "");
    }

    private void initConnection2() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            connexion2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/riche_existant?allowMultiQueries=true", "root", "");
    }

    private void initConnectionRiche() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            connexionRiche = DriverManager.getConnection("jdbc:mysql://localhost:3306/riche4", "root", "");
    }

    private String[] returnAuthorName(String input) {
        if (input != null) {
            String[] split = input.split(" and ");
            return split;
        }

        return new String[]{};
    }

    /**
     * *
     * Import les auteurs depuis la table t_source
     */
    private void importBiblioAuthor() throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement = connexion.createStatement();
            resultSet = statement.executeQuery("Select author from t_sources");
            while (resultSet.next()) {
                String authorsExistant = resultSet.getString("author");
                if (authorsExistant != null && !authorsExistant.isEmpty() && !authorsExistant.equals(" ")) {

                    String[] split = returnAuthorName(authorsExistant);

                    for (int i = 0; i < split.length; i++) {
                        String label = split[i];
                        Person p = new Person();
                        p.setLabel(label);

                        String[] tabNomPrenom = label.split(",");
                        if (tabNomPrenom != null && tabNomPrenom.length == 2) {
                            p.setFistName(tabNomPrenom[1]);
                            p.setLastName(tabNomPrenom[0]);
                        }

                        if (servicePerson.getPersonFromLabel(label).isEmpty()) {
                            servicePerson.addEntity(p);
                        }
                    }
                }
            }

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    log.error("erreur lors de la fermeture du statement", e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    log.error("erreur lors de la fermeture du resulset", e);
                }
            }
        }
    }

    private void importSource() throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connexion.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM t_sources");

            while (resultSet.next()) {
                Source source = new Source();
                source.setTitle(resultSet.getString("title"));
                Integer idSource = resultSet.getInt("numRef");

                Source existingSource = serviceSource.getEntity(new Long(idSource));
                if (existingSource == null) {

                    source.setId(new Long(idSource));
                    source.setVolume(resultSet.getInt("volume"));
                    source.setSeries(resultSet.getString("series"));
                    source.setPublisher(resultSet.getString("publisher"));
                    source.setEditor(resultSet.getString("editor"));
                    source.setReleaseYear(resultSet.getInt("year"));

                    String type_ref = resultSet.getString("type_ref");
                    source.setBibliographicType(mapBibliographicType.get(type_ref));
                    source.setJournal(resultSet.getString("journal"));
                    source.setUrl(resultSet.getString("url"));

                    if (serviceSource.getEntity(source.getId()) == null) {

                        String[] authorLabels = returnAuthorName(resultSet.getString("author"));

                        for (int i = 0; i < authorLabels.length; i++) {
                            String authorLabel = authorLabels[i];
                            List<Person> listPerson = servicePerson.getPersonFromLabel(authorLabel);
                            if (!listPerson.isEmpty()) {
                                RelationSourcePerson relationSourcePerson = new RelationSourcePerson();
                                relationSourcePerson.setSource(source);
                                relationSourcePerson.setPerson(listPerson.get(0));
                                source.addRelationPerson(relationSourcePerson);
                            }
                        }
                        try {
                            serviceSource.addEntity(source);
                        } catch (Exception e) {
                            log.error("Erreur creation SOURCE ", e);
                        }
                    }
                }

            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    log.error("erreur lors de la fermeture de resultset", e);
                }

            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    log.error("erreur lors de la fermeture de statement", e);
                }
            }
        }
    }

    private void importWork() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connexion.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM t_oeuvres");

            while (resultSet.next()) {

                WorkEntity workEntity = new WorkEntity();
                workEntity.setId(new Long(resultSet.getInt("ID_oeuvres")));
                workEntity.setTitle(resultSet.getString("Titre_oeuvre"));
                workEntity.setNote(resultSet.getString("Notes"));
                workEntity.setCenturyMax(resultSet.getInt("Siecle_Max"));
                workEntity.setCenturyMin(resultSet.getInt("Siecle_Min"));

                String workauthor = resultSet.getString("Auteur");
                if (workauthor != null && !workauthor.isEmpty() && !" ".equals(workauthor)) {
                    List<WorkAuthor> authorsFinded = serviceWorkAuthor.find(workauthor, false);
                    if (!authorsFinded.isEmpty()) {
                        workEntity.getAuthors().add(authorsFinded.get(0));
//                    serviceWorkAuthor.addEntity(serviceWorkAuthor.find(workauthor, false));
                    }
                }
                try {
                    workService.addEntity(workEntity);
                } catch (Exception e) {
                    log.error("impossible de créer OEUVRE ", e);
                }
            }

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    log.error("erreur lors de la fermeture du statement ", e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    log.error("Erreur lors de la fermeture du resultset", e);
                }
            }
        }
    }

    private void importWorkAuthors() {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connexion.createStatement();
            resultSet = statement.executeQuery("select Auteur from t_oeuvres group by Auteur");

            while (resultSet.next()) {
                WorkAuthor workAuthor = new WorkAuthor();
                String label = resultSet.getString("Auteur");
                if (label != null && !label.isEmpty() && !" ".equals(label)) {
                    workAuthor.setLabel(label);
                    serviceWorkAuthor.addEntity(workAuthor);

                }

            }

        } catch (Exception e) {
            log.error("erreur lors d el'import des WorkAuthor ", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    log.error("erreur lors de la fermeture du statement ", e);
                }
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception e) {
                        log.error("erreur lors de la fermeture du statement", e);
                    }
                }
            }

        }
    }

    @Transactional
    private void importRelation() throws SQLException {

//                         Import des sources
        Statement statement = null;
        ResultSet resultSetRelation = null;
        try {
            statement = connexion.createStatement();
            resultSetRelation = statement.executeQuery("SELECT * FROM t_liaison_sources_oeuvres");
            int i = 0;
            while (resultSetRelation.next()) {
                RelationWorkSource relationWorkSource = new RelationWorkSource();

                WorkEntity workEntity = workService.getEntity(new Long(resultSetRelation.getInt("ID_oeuvres")));
                Source source = serviceSource.getEntity(new Long(resultSetRelation.getInt("ID_sources")));

                relationWorkSource.setWorkEntity(workEntity);

                relationWorkSource.setSource(source);
                relationWorkSource.setExtract(resultSetRelation.getString("Pages"));
//                workEntity.addRelationWorkSource(relationWorkSource);

                if (source != null && workEntity != null) {
                    daoRelationWorkSource.addEntity(relationWorkSource);
//                    workService.modifyEntity(workEntity);
                }
                if (i > 11) {
                    System.out.println("===================");
                    System.out.println("-->FLUSHHH");
                    System.out.println("===================");
                    i = 0;
                    daoRelationWorkSource.clearSession();
//                    daoRelationWorkSource.getSessionFactory().getCurrentSession().clear();
                }
                i++;

            }
        } finally {
            if (resultSetRelation != null) {
                try {
                    resultSetRelation.close();
                } catch (Exception e) {
                    log.error("erreur lors de la fermeture du resultaset ", e);
                }
            }

        }
    }

    @Transactional
    private void importRelation2() throws SQLException {

//                         Import des sources
        Statement statement = null;
        ResultSet resultSetRelation = null;
        try {
            statement = connexion.createStatement();
            resultSetRelation = statement.executeQuery("SELECT * FROM t_liaison_sources_oeuvres");
            int i = 0;
            while (resultSetRelation.next()) {

                String extract = resultSetRelation.getString("Pages");
                String nature = "";
                String note = "";
                Long source_id = new Long(resultSetRelation.getInt("ID_sources"));
                Long workEntity_id = new Long(resultSetRelation.getInt("ID_oeuvres"));
                StringBuilder builder = new StringBuilder();
                builder.append("INSERT INTO RelationWorkSource(extract, nature, note, source_id, workEntity_id) VALUES (");
                builder.append("'");
                builder.append(extract);
                builder.append("', ");
                builder.append("'");
                builder.append(nature);
                builder.append("', ");
                builder.append("'");
                builder.append(note);
                builder.append("', ");
                builder.append(source_id);
                builder.append(", ");
                builder.append(workEntity_id);
                builder.append(")");

                Statement statementRiche = null;
                ResultSet resultSet = null;

                WorkEntity existingEntity = workService.getEntity(workEntity_id);
                Source existingSource = serviceSource.getEntity(source_id);
                if (existingEntity == null) {
                    log.error("Impossible de créer une relation. Oeuvre non trouve id : " + workEntity_id);
                    continue;
                }
                if (existingSource == null) {
                    log.error("Impossible de créer une relation. Source non trouvée id : " + source_id);
                    continue;

                }
                try {

                    statementRiche = connexionRiche.createStatement();
                    statementRiche.executeUpdate(builder.toString());
                } catch (Exception e) {
                    log.error("Erreur ", e);
                } finally {
                    

                }

            }
        } finally {
            if (resultSetRelation != null) {
                try {
                    resultSetRelation.close();
                } catch (Exception e) {
                    log.error("erreur lors de la fermeture du resultaset ", e);
                }
            }

        }

    }

    @Override
    public void initThemes() {
        Theme t0 = new Theme("clercs et novices");
        Theme t1 = new Theme("conseils aux femmes");
        Theme t2 = new Theme("courtoisie");
        Theme t4 = new Theme("éducation politique");
        Theme t5 = new Theme("éducation spirituelle");
        Theme t6 = new Theme("maîtres et écoliers");
        Theme t7 = new Theme("parents et enfants");
        Theme t8 = new Theme("éducation morale (bonnes manières)");
        Theme t9 = new Theme("éducation morale (échecs moralisés)");
        Theme t10 = new Theme("éducation morale (journées)");

        mapTheme.put(new Long(0), t0);
        mapTheme.put(new Long(1), t1);
        mapTheme.put(new Long(2), t2);
        mapTheme.put(new Long(4), t4);
        mapTheme.put(new Long(5), t5);
        mapTheme.put(new Long(6), t6);
        mapTheme.put(new Long(7), t7);
        mapTheme.put(new Long(8), t8);
        mapTheme.put(new Long(9), t9);
        mapTheme.put(new Long(10), t10);

        themeService.addEntity(t0);
        themeService.addEntity(t1);
        themeService.addEntity(t2);
        themeService.addEntity(t4);
        themeService.addEntity(t5);
        themeService.addEntity(t6);
        themeService.addEntity(t7);
        themeService.addEntity(t8);
        themeService.addEntity(t9);
        themeService.addEntity(t10);

    }

    public void importBDD() {
        try {

            initConnection();
            initConnection2();
            initConnectionRiche();
            initbibliographicType();
            initThemes();
//        importSource();
            importBiblioAuthor();
            importSource();
            importWorkAuthors();
            importWork();
            importRelation2();
            importRelationWorkTheme();
//            importRelation();
        } catch (Exception e) {
            log.error("erreur lors de l'import de l'ancienne base", e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (Exception e) {
                    log.error("erreur lors de la fermeture de la base de données ", "");
                }
            }
        }
    }

    public SourceServiceIfs getServiceSource() {
        return serviceSource;
    }

    public void setServiceSource(SourceServiceIfs serviceSource) {
        this.serviceSource = serviceSource;
    }

    public void initbibliographicType() {
        BibliographicType t1 = new BibliographicType("Article dans une revue");
        BibliographicType t2 = new BibliographicType("Ouvrage");
        BibliographicType t3 = new BibliographicType("Article dans un collectif");
        BibliographicType t4 = new BibliographicType("Manuscrit");
        BibliographicType t5 = new BibliographicType("Mémoire ou thèse");

        serviceBibliographicType.addEntity(t1);
        serviceBibliographicType.addEntity(t2);
        serviceBibliographicType.addEntity(t3);
        serviceBibliographicType.addEntity(t4);
        serviceBibliographicType.addEntity(t5);

        mapBibliographicType.put("article", t1);
        mapBibliographicType.put("book", t2);
        mapBibliographicType.put("incollection", t3);
        mapBibliographicType.put("Manuscrit", t4);
        mapBibliographicType.put("phdthesis", t5);

    }

    public BibliographicTypeServiceIfs getServiceBibliographicType() {
        return serviceBibliographicType;
    }

    public void setServiceBibliographicType(BibliographicTypeServiceIfs serviceBibliographicType) {
        this.serviceBibliographicType = serviceBibliographicType;
    }

    public PersonServiceIfs getServicePerson() {
        return servicePerson;
    }

    public void setServicePerson(PersonServiceIfs servicePerson) {
        this.servicePerson = servicePerson;
    }

    public ThemeServiceIfs getThemeService() {
        return themeService;
    }

    public void setThemeService(ThemeServiceIfs themeService) {
        this.themeService = themeService;
    }

    public WorkAuthorServiceIfs getServiceWorkAuthor() {
        return serviceWorkAuthor;
    }

    public void setServiceWorkAuthor(WorkAuthorServiceIfs serviceWorkAuthor) {
        this.serviceWorkAuthor = serviceWorkAuthor;
    }

    public WorkServiceIfs getWorkService() {
        return workService;
    }

    public void setWorkService(WorkServiceIfs workService) {
        this.workService = workService;
    }

    public DAORelationWorkSourceIfs getDaoRelationWorkSource() {
        return daoRelationWorkSource;
    }

    public void setDaoRelationWorkSource(DAORelationWorkSourceIfs daoRelationWorkSource) {
        this.daoRelationWorkSource = daoRelationWorkSource;
    }

    private void importRelationWorkTheme() {
//                Statement statement = null;
        ResultSet resultSetRelation = null;
        try (Statement statement = connexion.createStatement()){
//            statement = connexion.createStatement();
            resultSetRelation = statement.executeQuery("SELECT * FROM t_themes");
            int i = 0;
            while (resultSetRelation.next()) {
                
                Integer idOeuvre=resultSetRelation.getInt("ID_Oeuvres");
                Integer idTypeOeuvre = resultSetRelation.getInt("ID_Typ_themes");
                
                Theme theme =  mapTheme.get(new Long(idTypeOeuvre));
                WorkEntity work = workService.getEntity(new Long(idOeuvre));

                if(theme != null && work != null){
                work.getTheme().add(theme);
                workService.modifyEntity(work);                    
                }

            }
            
        } catch (SQLException ex) {
            log.error("Erreur SQL", ex);
        }
        catch(Exception e){
            log.error("Erreur ?! ", e);
        }
    }
}
