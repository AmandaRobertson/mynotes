package com.ssf.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;


public class JDBCUtils 
{
   //public static final int MAX_VALUE=10;
	
   //1.˽�л�������
  private JDBCUtils(){}
  //2.
  //private  JDBCUtils _instance2 = new JDBCUtils();
  private static JDBCUtils _instance = new JDBCUtils();
  //3.
  public static JDBCUtils getInstance(){
	  return _instance;
  }
  
  static Connection connection = null;
  
   void closeConnection() throws SQLException {
		try {
			if (null != connection)
				connection.close();
		} finally {
			connection = null;
			System.gc();	
		}
	}
  
  //1.�����ݿ�����
  Connection openConnection() throws ClassNotFoundException, SQLException{
	  
	  String driver = "com.mysql.jdbc.Driver";
	  String url    = "jdbc:mysql://127.0.0.1:3306/bookstore";
	  String username = "root";
	  String pwd = "";
	  
	  //1.�����ൽJVM����ȥ
	  Class.forName(driver);
	  //2.DriverManager��ȡ��ݿ�����
	  connection = DriverManager.getConnection(url,username,pwd);
	  return connection;
  }
   <T> ArrayList<T> getArrayListByCate(String sql,Class<T> beanclass,Object...params) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, InvocationTargetException{
	   Connection connection=openConnection();
//	   String sql="select * from "+str;
		PreparedStatement ptmt=connection.prepareStatement(sql);
		for(int i=0;i<params.length;i++)
			ptmt.setObject(i+1, params[i]);
		
		ResultSet rs=ptmt.executeQuery();
		ArrayList<T> lists=getArrayListByRs(rs,beanclass);
		closeConnection();			
		return lists;
	  
  }
  
  private <T> ArrayList<T> getArrayListByRs(ResultSet rs,Class<T> beanclass) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException{
	  ArrayList<T> lists=new ArrayList<T>();
	  ResultSetMetaData rsd=rs.getMetaData();
	  int columns=rsd.getColumnCount();
	  while(rs.next()){
		  T cls=beanclass.newInstance();
		  Map<String,Object> map=new HashMap<String,Object>();
		  for(int i=0;i<columns;i++){
			  String key=rsd.getColumnName(i+1);
			  Object value=rs.getObject(key);
			  map.put(key, value);
			  BeanUtils.populate(cls, map);			  
			  
		  }
		  lists.add(cls);
	  }return lists;
	 
  }
 public <T> ArrayList<T> query_pre(String sql,Class<T> beanclass,Object...params) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, InvocationTargetException{
	Connection connection=openConnection();
	PreparedStatement ptmt=connection.prepareStatement(sql);
	for(int i=0;i<params.length;i++)
		ptmt.setObject(i+1, params[i]);
		ResultSet rs=ptmt.executeQuery();
		ArrayList<T> lists=getArrayListByRs(rs,beanclass);
		closeConnection();			
	return lists;
	
}

//public <T> ArrayList<T> query_preIndex(String sql,Class<T> beanclass,int offset,int number,Object...params){
//	Connection connection=openConnection();
//	PreparedStatement ptmt=connection.prepareStatement(sql);
//	for(int i=0;i<params.length;i++)
//		ptmt.setObject(i+1, params[i]);
//		ResultSet rs=ptmt.executeQuery();
//		ArrayList<T> lists=getArrayListByRs(rs,beanclass);
//		closeConnection();			
//	return lists;
//	
//}


  //2.������ݿ����ӽ�����ݿ����
// public  Book query(String sql) throws ClassNotFoundException, SQLException{	
//	 Book book=null;
//	  //1.��������
//	  Connection connection= openConnection();
//	  if(connection!=null){
//		  //2.����statement��������SQL���
//		  Statement stmt = connection.createStatement();
//		  //3.���ResultSet���洢���ؽ��
//		  ResultSet rs= stmt.executeQuery(sql);
//		  //4.����ݿ������ӳ���JavaBean
//		  while (rs.next()) {
//			  
//			  String bookname = rs.getString("bookname");
//			  String category = rs.getString("category");
//			  double price=Double.parseDouble(rs.getString("price"));
//			  book = new Book(bookname, category,price);		
//		  } 
//	  }
//	  //5.�ر�����(��ݿ������ر�ռ��Դ)
//	  closeConnection(); 
//	  return book;
//  }
 
// public  Book queryById(String sql) throws ClassNotFoundException, SQLException{	
//	 Book book=null;
//	  //1.��������
//	  Connection connection= openConnection();
//	  if(connection!=null){
//		  //2.����statement��������SQL���
//		  Statement stmt = connection.createStatement();
//		  //3.���ResultSet���洢���ؽ��
//		  ResultSet rs= stmt.executeQuery(sql);
//		  //4.����ݿ������ӳ���JavaBean
//		  while (rs.next()) {
//			  
//			  String bookname = rs.getString("bookname");
//			  String category = rs.getString("category");
//			  double price=Double.parseDouble(rs.getString("price"));
//			  String img=rs.getString("img");
//			  book = new Book(bookname, category,price,img);		
//		  } 
//	  }
//	  //5.�ر�����(��ݿ������ر�ռ��Դ)
//	  closeConnection(); 
//	  return book;
//  }
// 
  
  public <T> ArrayList<T> queryt(String sql,Class<T> bean) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, InvocationTargetException
  {
	  ArrayList<T> lists = new ArrayList<T>();
	  Connection connection= openConnection();
		  if(connection!=null){
			  Statement stmt = connection.createStatement();
			  ResultSet rs= stmt.executeQuery(sql);
			  ResultSetMetaData rsd=  rs.getMetaData();//Ԫ���-�����ݵ����
			  int columns = rsd.getColumnCount();//1.�м���
			  while(rs.next())
			  {
				  T cls = bean.newInstance();
				  //1.Map-��һ����ݳ���
				  Map<String,Object> map =new HashMap<String, Object>();
				  for(int i=0;i<columns;i++){
						 String key = rsd.getColumnName(i+1);
						 Object value = rs.getObject(key);
						 map.put(key, value);
						 BeanUtils.populate(cls, map);
						 
				  } 
				  lists.add(cls);
			  }
			  
		  }
		  //5.�ر�����(��ݿ������ر�ռ��Դ)
		  closeConnection();
		  return lists;
  }
  /*
  //����user�ļ���
  public ArrayList<User> querybean(String sql) throws ClassNotFoundException, SQLException, IllegalAccessException, InvocationTargetException{
	  Connection connection= openConnection();
	//2.��list��ÿһ��
	  ArrayList<User> lists = new ArrayList<User>();
	 
	  if(connection!=null){
		  Statement stmt = connection.createStatement();
		  ResultSet rs= stmt.executeQuery(sql);
		  ResultSetMetaData rsd=  rs.getMetaData();//Ԫ���-�����ݵ����
		  int columns = rsd.getColumnCount();//1.�м���
		  while(rs.next())
		  {
			  //1.Map-��һ����ݳ���
			  Map<String,Object> map =new HashMap<String, Object>();
			  for(int i=0;i<columns;i++){
				String key = rsd.getColumnName(i+1);
				Object value = rs.getObject(key);
				map.put(key, value);
			    User user = new User();
				BeanUtils.populate(user, map);
				lists.add(user);
			  } 
		  }
		  
	  }
	  //5.�ر�����(��ݿ������ر�ռ��Դ)
	  closeConnection();
	  return lists;
  }
  */
  //1.��ѯ  SELECT * FROM t_user
  //2.����  insert into t_user(username,password) values("","")
  //3.�޸�  update t_user set username = '�޸�' where id=3;
  //4.ɾ��  delete from t_user WHERE id=3
  public void insert_update(String sql,Object...params) throws ClassNotFoundException, SQLException{
	  Connection connection= openConnection();
	  PreparedStatement ptmt = connection.prepareStatement(sql);
	  int i = 0;
	  for(Object obj:params)
	  {
		  i = i+1;
		  ptmt.setObject(i, obj);//1
	  }
	  
	  ptmt.executeUpdate();
	  closeConnection();
  }
  //
  public void insertImg(String sql,Object...params) throws ClassNotFoundException, SQLException{
	  Connection connection= openConnection();
	  PreparedStatement ptmt = connection.prepareStatement(sql);
	  int i = 0;
	  for(Object obj:params)
	  {
		  i = i+1;
		  ptmt.setObject(i, obj);//1
	  }
	  
	  ptmt.executeUpdate();
	  closeConnection();
  }
  
  public void delete(String sql) throws ClassNotFoundException, SQLException{
	  Connection connection= openConnection();
      Statement stmt=connection.createStatement();
      stmt.execute(sql);
      stmt.close();
      closeConnection();
  }
  
   public void batchInsert() throws ClassNotFoundException, SQLException{
	   //1.��������
	   Connection connection= openConnection();
	   //2.�ر��Զ�����
	   connection.setAutoCommit(false);
	   String sql="insert into books(bookname,category,price) values(?,?,?)";
	   PreparedStatement ptmt=connection.prepareStatement(sql);
	   for(int i=0;i<10000;i++){
		   ptmt.setObject(1, "����"+i); 
		   ptmt.setObject(2, "�����"); 
		   ptmt.setObject(3, i*Math.random());
		   ptmt.addBatch();
	   }
	   
	   
//	   //3.sqlƴ��
//		StringBuffer suffix=new StringBuffer();
//		  for(int i=0;i<10000;i++){
//			 suffix.append("('����"+i+"','�����',"+(i*Math.random())+"),");
//		  }
//		  String sql=prefix+suffix.substring(0,suffix.length()-1);
//		  //batch����-��ͬ��sql�����ҪaddBatchһ��
//		 ptmt.addBatch(sql);
	   
	   
		 ptmt.executeBatch();
		 connection.commit();
		 //5.�ύ����
		 closeConnection();
   }
   //
   public void batchInsert2() throws ClassNotFoundException, SQLException{
	   //1.��������
	   Connection connection= openConnection();
	   //2.�ر��Զ�����
	   connection.setAutoCommit(false);
	   String prefix="insert into books(bookname,category,price) values ";
	   PreparedStatement ptmt=connection.prepareStatement("");
	   //3.sqlƴ��
		StringBuffer suffix=new StringBuffer();
		  for(int i=0;i<10000;i++){
			 suffix.append("('����"+i+"','�����',"+(i*Math.random())+"),");
		  }
		  String sql=prefix+suffix.substring(0,suffix.length()-1);
		 //batch����-��ͬ��sql�����ҪaddBatchһ��
		 ptmt.addBatch(sql);
	   
		 ptmt.executeBatch();
		 connection.commit();
		 //5.�ύ����
		 closeConnection();
   }
   
 
  public void insert_update_pre(String sql,Object...params) throws ClassNotFoundException, SQLException{
	  Connection connection= openConnection();
	  PreparedStatement ptmt = connection.prepareStatement(sql);
	  int i = 0;
	  for(Object obj:params)
	  {
		  i = i+1;
		  ptmt.setObject(i, obj);//1
	  }
	  
	  ptmt.executeUpdate();
	  closeConnection();
  }
  

  
  public static void main(String[] args) {
	 try {
		 //String sql = "SELECT * FROM t_user";
		 //ArrayList<Object> lists = JDBCUtils.getInstance().queryt(sql,User.class);
	     //System.out.println(lists);
		 
		 String sql = "insert into t_user(username,password) values('SSSSSSS','123456')";
		 //String sql = "UPDATE t_user SET username = '�޸�' WHERE id=3";
//		 int flag =JDBCUtils.getInstance().insert_update(sql);
//		 System.out.println("flag="+flag);
		 //String sql_del = "delete from t_user WHERE id=3";
		 //JDBCUtils.getInstance().insert_update(sql_del);
		 
	 }catch (Exception e) {
		e.printStackTrace();
	} 
  }
  
}