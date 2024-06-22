
package utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import baseUtil.*;

public class AssistantDB
{
	Connection connection;
	public Connection getConnection()
	{
		return this.connection;
	}
	public void setConnection(Connection co)throws Exception
	{
		if (co==null) {
			throw new Exception("Connection invalide");
		}
		this.connection=co;
	}
	public AssistantDB(Connection co)
	{
		try
		{
			this.setConnection(co);	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	public AssistantDB()
	{
		try{
			this.setConnection(ConnexionPgsql.dbConnect());
		}	
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public Statement getStatement()throws Exception
	{
		Statement st=this.connection.createStatement();
		return st;
	}
	public ResultSet query(String requetes)throws Exception
	{
		System.out.println(requetes);
		Statement st=this.getStatement();
		ResultSet result=st.executeQuery(requetes);
		return result;
	}
	public int update(String requetes)throws Exception
	{
		Statement st=this.getStatement();
		System.out.println(requetes);
		int upd=st.executeUpdate(requetes);
		return upd;
	}
	public int getNombreData(String requetes)
	{
		int k=0;
		try
		{
			ResultSet result=this.query(requetes);
			while(result.next())
			{
				k++;
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		finally
		{
			return k;
		}
		
	}
	public int getNombreColumn(String requetes)
	{
		int k=0;
		try
		{
			ResultSet result=this.query(requetes);
			ResultSetMetaData tableau=result.getMetaData();
			k=tableau.getColumnCount();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return k;
	}
	public String[] getListeColumn(String requetes)throws Exception
	{
		ResultSet result=this.query(requetes);
		ResultSetMetaData tableau=result.getMetaData();
		int taille=getNombreColumn(requetes);
		String[] listeNomColumn=new String[taille];
		for (int i=0;i<taille;i++)
		{
			listeNomColumn[i]=tableau.getColumnName(i+1);	
		}
		return listeNomColumn;
	}
	public Object[][] getDonnees(String requetes)
	{
		int nombreData=getNombreData(requetes);
		int nombreColumn=getNombreColumn(requetes);
		Object[][] liste=new Object[nombreData][nombreColumn];
		try
		{
			int k=0;
			ResultSet result=this.query(requetes);
			while(result.next())
			{
				Object[] sousListe=new Object[nombreColumn];
				for (int i=0;i<nombreColumn;i++) 
				{
					if (result.getObject(i+1) instanceof BigDecimal)
					{
						sousListe[i]=((BigDecimal)result.getObject(i+1));
					}
					if (result.getObject(i+1) instanceof Integer)
					{
						sousListe[i]=((Integer)result.getObject(i+1));
					}
					if (result.getObject(i+1) instanceof Time)
					{
						sousListe[i]=((Time)result.getObject(i+1));
					}
					if (result.getObject(i+1) instanceof String || result.getObject(i+1) instanceof Timestamp || result.getObject(i+1) instanceof Date )
					{
						sousListe[i]=result.getObject(i+1);
					}
					
				}
				liste[k]=sousListe;
				k++;
			}
			result.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			return liste;
		}
		
	}

	public List<Object> getData(String request,Object o){
		System.out.println(request);
		List<Object> toReturn = new ArrayList<>();
		try {
			Class myclass = o.getClass();
			Statement statement = this.getConnection().createStatement();
			ResultSet result=statement.executeQuery(request);
			int k=result.getMetaData().getColumnCount();

			while (result.next()){
				Object toInsert = myclass.newInstance();
				Field[] fields = myclass.getDeclaredFields();
				for (int i = 0; i < k; i++) {
					fields[i].setAccessible(true);
				    fields[i].set(toInsert, result.getObject(i+1));;
					fields[i].setAccessible(false);
				}
				toReturn.add(toInsert);
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			return toReturn;
		}
	}
}





