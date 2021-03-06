package org.admin.dao.impl;

import java.sql.*;

import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;
import org.admin.dao.StudentDao;
import org.admin.beans.scolarite.Student;
import org.admin.beans.scolarite.Inscription;
import java.util.List;
import java.util.ArrayList;

public class StudentDaoImpl implements StudentDao{
	private DaoFactory daoFactory;

	public StudentDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	public List<Student> getAllStudentByParcours(int id_parcours) throws DaoException{
		List<Student> students = new ArrayList<Student>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM \"Vue_Inscription_Etudiant\" WHERE id_parcours = ?";
			//String query = "SELECT * FROM \"Vue_parcours_responsable_n_etudian\" WHERE id_parcours = ?";
			//System.out.println("Test0");
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_parcours);
			resultat = preparedStatement.executeQuery();
			//System.out.println("Andrana");
			while(resultat.next()){
				//System.out.println(query);
				
				int id = resultat.getInt("id_etudiant");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String ddn = resultat.getString("ddn");
				String adresse = resultat.getString("adresse");
				String lieu_naissance = resultat.getString("lieu_naissance");
				String tel = resultat.getString("tel");
				String nom_mention = resultat.getString("nom_mention");
				String nom_parcours = resultat.getString("nom_parcours");
				//int id_parcours = getIdparcours(nom_parcours);
				String niveau = resultat.getString("niveau");
				String annee_u = resultat.getString("annee_u");
				String situation = resultat.getString("situation");
				//String grade = resultat.getString("grade");

				Student student = new Student();
				student.setId_etudiant(id);
				student.setId_parcours(id_parcours);
				student.setNom(nom);
				student.setPrenom(prenom);
				student.setDdn(ddn);
				student.setAdresse(adresse);
				student.setNiveau(niveau);
				student.setLieu_naissance(lieu_naissance);
				student.setTel(tel);
				student.setNom_mention(nom_mention);
				student.setNom_parcours(nom_parcours);
				student.setAnnee_u(annee_u);
				student.setSituation(situation);
				//System.out.println(student);
				
				students.add(student);

				//String var = student.toString();
				
				
			}

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			System.out.println("Erreur SQL: "+e.getMessage());
			throw new DaoException("Erreur SQL: "+e.getMessage());
			
		}

		return students;
	}
	
	public List<Student> getAllStudentByParcours(int id_parcours,String niveau) throws DaoException{
		List<Student> students = new ArrayList<Student>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM \"Vue_Inscription_Etudiant\" WHERE id_parcours = ? and niveau= ? ";
			//String query = "SELECT * FROM \"Vue_parcours_responsable_n_etudian\" WHERE id_parcours = ?";
			//System.out.println("Test0");
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_parcours);
			preparedStatement.setString(2, niveau);
			resultat = preparedStatement.executeQuery();
			//System.out.println("Andrana");
			while(resultat.next()){
				//System.out.println(query);
				
				int id = resultat.getInt("id_etudiant");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String ddn = resultat.getString("ddn");
				String adresse = resultat.getString("adresse");
				String lieu_naissance = resultat.getString("lieu_naissance");
				String tel = resultat.getString("tel");
				String nom_mention = resultat.getString("nom_mention");
				String nom_parcours = resultat.getString("nom_parcours");
				//int id_parcours = getIdparcours(nom_parcours);
				//String niveau = resultat.getString("niveau");
				String annee_u = resultat.getString("annee_u");
				String situation = resultat.getString("situation");
				//String grade = resultat.getString("grade");

				Student student = new Student();
				student.setId_etudiant(id);
				student.setId_parcours(id_parcours);
				student.setNom(nom);
				student.setPrenom(prenom);
				student.setDdn(ddn);
				student.setAdresse(adresse);
				student.setNiveau(niveau);
				student.setLieu_naissance(lieu_naissance);
				student.setTel(tel);
				student.setNom_mention(nom_mention);
				student.setNom_parcours(nom_parcours);
				student.setAnnee_u(annee_u);
				student.setSituation(situation);
				//System.out.println(student);
				
				students.add(student);

				//String var = student.toString();
				
				
			}

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			System.out.println("Erreur SQL: "+e.getMessage());
			throw new DaoException("Erreur SQL: "+e.getMessage());
			
		}

		return students;
	}


	public int getIdparcours(String nom_parcours) throws DaoException{
		int id = 0;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM t_parcours WHERE nom_parcours = ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, nom_parcours);
			resultat = preparedStatement.executeQuery();
			resultat.next();
			id = resultat.getInt("id_parcours");
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return id;
	}

	public Student getStudentById(int id_etudiant) throws DaoException{
		Student listById = new Student();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM t_import_inscription WHERE id_etudiant = ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_etudiant);
			resultat = preparedStatement.executeQuery();
			resultat.next();
			listById.setNom(resultat.getString("nom"));
			listById.setPrenom(resultat.getString("prenom"));

		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		System.out.println(listById.toString());
		return listById;
	}

	public String getNomParcoursById(Inscription inscirs) throws DaoException{
		String parcours = null;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM t_parcours WHERE id_parcours = ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, inscirs.getId_parcours());
			resultat = preparedStatement.executeQuery();
			resultat.next();
			parcours = resultat.getString("nom_parcours");
		}catch (SQLException e) {
			throw new DaoException("Erreur SQL: "+e.getMessage());
		}

		System.out.println(parcours);
		return parcours;
	}

	public List<Student> getAllListStudentByParcours(String parcours,String niveau) throws DaoException{
		List<Student> students = new ArrayList<Student>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM \"Vue_Inscription_Etudiant\" WHERE nom_parcours = ? AND niveau= ? ";
			
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1,parcours);
			preparedStatement.setString(2, niveau);
			resultat = preparedStatement.executeQuery();
			//System.out.println("Andrana");
			while(resultat.next()){
				//System.out.println(query);
				
				int id = resultat.getInt("id_etudiant");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String ddn = resultat.getString("ddn");
				String adresse = resultat.getString("adresse");
				String lieu_naissance = resultat.getString("lieu_naissance");
				String tel = resultat.getString("tel");
				//int id_parcours = getIdparcours(nom_parcours);
				//String niveau = resultat.getString("niveau");
				String annee_u = resultat.getString("annee_u");
				String situation = resultat.getString("situation");
				//String grade = resultat.getString("grade");

				Student student = new Student();
				student.setId_etudiant(id);
				//student.setId_parcours(id_parcours);
				student.setNom(nom);
				student.setPrenom(prenom);
				student.setDdn(ddn);
				student.setAdresse(adresse);
				student.setNiveau(niveau);
				student.setLieu_naissance(lieu_naissance);
				student.setTel(tel);
				student.setNom_parcours(parcours);
				student.setAnnee_u(annee_u);
				student.setSituation(situation);
				//System.out.println(student);
				
				students.add(student);

				//String var = student.toString();
				System.out.println(nom+"  " + prenom + " "+ ddn);
				 
				
			}

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch (SQLException e) {
			System.out.println("Erreur SQL: "+e.getMessage());
			throw new DaoException("Erreur SQL: "+e.getMessage());
			
		}

		return students;
	}

}
