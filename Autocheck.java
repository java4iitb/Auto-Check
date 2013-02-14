import java.io.*;
import java.sql.*;
import java.util.*;
public class Autocheck
{

  public static void main(String arg[])
  {
  try
  {
  int quizid=0;
  int item=0;

  if(arg[0]!=null)
  {
  quizid=Integer.parseInt(arg[0]);
  }

 String language = arg[1];
 String extension = null;
 String ofilename = null; 

if(language.equalsIgnoreCase("C"))
 {
   extension = ".c";
   item = 16;
 }
else if((language.equalsIgnoreCase("C++")) || (language.equalsIgnoreCase("CPP")))
{
  extension = ".cpp";
  item = 31;
}
else if(language.equalsIgnoreCase("java"))
{
 extension = ".java";
}
else
{
    System.out.println("Enter a proper language. Thank you!!!!");
    System.exit(0);
}

    String filename = null;
    File file=null;
    String location=null; String dir_path=null;
    int ccc=0;
    String question=null;
    String key=null;
    int update =0;
    double sumgrades=0;
    double average=0;
  
    ArrayList<Integer> userid = new ArrayList<Integer>();
    ArrayList<Integer> uniqueid = new ArrayList<Integer>();

    ArrayList<Integer> attemptid = new ArrayList<Integer>();
    ArrayList<Integer> attemptid2 = new ArrayList<Integer>();
    
    ArrayList<Integer> questionid = new ArrayList<Integer>();
    ArrayList<String> behaviour = new ArrayList<String>();
    ArrayList<String> answer = new ArrayList<String>();
    ArrayList<String> keyword = new ArrayList<String>();

    try
    {
     Class.forName("com.mysql.jdbc.Driver");
//     Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/OTC","javauser","javauser123*");
     Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/newmoodle2_4","root","root");
     Statement sts1 = con.createStatement();
     Statement sts2 = con.createStatement();
     
     Statement sts3 = con.createStatement();
     Statement sts4 = con.createStatement();
     Statement sts5 = con.createStatement();
     Statement sts6 =con.createStatement();
     Statement sts7 = con.createStatement();
     
   ResultSet rs = sts1.executeQuery("select userid, uniqueid from mdl_quiz_attempts where quiz="+quizid+" and sumgrades is NULL;");
    //  ResultSet rs = sts1.executeQuery("select userid, uniqueid from mdl_quiz_attempts where quiz="+quizid+";");
    
    
   /* ResultSet rs=sts1.executeQuery( "select a.userid,b.responsesummary,c.fraction,c.state from mdl_quiz_attempts a inner join mdl_question_attempts b on a.uniqueid=b.questionusageid inner join  mdl_question_attempt_steps c on c.questionattemptid = b.id where a.userid=2 and a.quiz=7;");*/
    ResultSetMetaData rsmd = rs.getMetaData();
    
    System.out.print(""+(rsmd.getColumnName(1)));
   // System.out.print("\t\t"+(rsmd.getColumnName(2)));
   // System.out.printx("\t\t"+(rsmd.getColumnName(3)));
    //System.out.println("\t\t"+(rsmd.getColumnName(4)));
    int j =0;
    
    
    while(rs.next())
    {
      userid.add(Integer.parseInt(rs.getString(1)));
      System.out.print(userid.get(j));
      uniqueid.add(Integer.parseInt(rs.getString(2)));
      System.out.print(uniqueid.get(j));
      j++;
      //System.out.print("\t\t"+rs.getString(2));
      //System.out.print("\t\t"+rs.getString(3));
      //System.out.println("\t\t"+rs.getString(4));
    }
    //rs.last();
     //int i =rs.getRow();
    System.out.println("\n Number of rows  "+ userid.size());
    
    
   
    
    
    for(int x=0; x < userid.size();x++ )
    {
     ResultSet rs2 =sts2.executeQuery("select qa.id, qa.questionid,qa.behaviour,qa.responsesummary, q.name from mdl_question_attempts qa, mdl_question q where qa.questionid = q.id and behaviour='manualgraded' and questionusageid = "+uniqueid.get(x)+";");
  
    int k=0;
    while(rs2.next())
    {
     attemptid.add(Integer.parseInt(rs2.getString(1)));
     questionid.add(Integer.parseInt(rs2.getString(2)));
     behaviour.add(rs2.getString(3));
     answer.add(rs2.getString(4));
     keyword.add(rs2.getString(5));
    
     System.out.println(attemptid.get(k)+" "+questionid.get(k)+" "+ behaviour.get(k)+" "+answer.get(k)+" "+keyword.get(k));
     k++;
    }
   
   ResultSet rs4 =sts5.executeQuery("select qa.id from mdl_question_attempts qa, mdl_question q where qa.questionid = q.id and questionusageid = "+uniqueid.get(x)+";");
  
   while(rs4.next())
   {attemptid2.add(Integer.parseInt(rs4.getString(1)));} 
   System.out.println("values   "+attemptid2);
   
    
    try
    {
    // dir_path="/home/javauser/testing_v2/cache/"+userid.get(x)+uniqueid.get(x)+"";
    dir_path="/home/ttt/moodle-test/"+userid.get(x)+uniqueid.get(x)+"";

    File dir = new File(dir_path);
    dir.mkdir();
    }catch(Exception ex){System.out.println("   2nd main   "+ex);} 
    for(int z = 0; z < questionid.size(); z++ )
    {
  	
     try
     {	 
     String rep =answer.get(z);
     if(rep==null)
     {rep="";}
     String w = rep.replaceAll(String.valueOf((char)160)," ").trim();
    ofilename = keyword.get(z)+"";
    filename = ofilename+extension+"";
    //file = new File(dir_path+"/"+filename);
    
      
    FileWriter fstream = new FileWriter(dir_path+"/"+filename);
        BufferedWriter out = new BufferedWriter(fstream);
    out.write(w);
    //Close the output stream
    out.close();
  
    
    
    /*FileOutputStream fos = new FileOutputStream(file);
    DataOutputStream dos = new DataOutputStream(fos);
    char[] ch = w.toCharArray();
    for(int i=0;i< ch.length;i++)
    {
       int b = (int)ch[i];
       dos.write(b);
    }
    */
    
    
    key=keyword.get(z);
    location =dir_path;
    }catch(Exception e1){System.out.println("e1 :"+e1);}   
   if(key.equals("Addition"))
    {	
       Testcode_interface ttt = new Testcode_implementation();
       ccc = ttt.additiontest(location,filename,language,ofilename);
       System.out.println("\n\n\n\nfrom face add "+ccc);
     }
     else if(key.equals("SumCheck"))
     {
      Testcode_interface ttt = new Testcode_implementation();
       ccc = ttt.sumchecktest(location,filename,language,ofilename);
       System.out.println("\n\n\n\nfrom face sum "+ccc);
     }
     else if(key.equals("Perimeter"))
     {
        Testcode_interface ttt = new Testcode_implementation();
       ccc = ttt.perimetertest(location,filename,language,ofilename);
       System.out.println("\n\n\n\nfrom face peri "+ccc);
     }
     else if(key.equals("SimpleInterest"))
     {
       Testcode_interface ttt = new Testcode_implementation();
       ccc = ttt.interesttest(location,filename,language,ofilename);
       System.out.println("\n\n\n\nfrom face  interest"+ccc);
     }
      else if(key.equals("Average"))
     {
       Testcode_interface ttt = new Testcode_implementation();
       ccc = ttt.averagetest(location,filename,language,ofilename);
       System.out.println("\n\n\n\nfrom face  average"+ccc);
     }
       else if(key.equals("Cube"))
     {
       Testcode_interface ttt = new Testcode_implementation();
       ccc = ttt.cubetest(location,filename,language,ofilename);
       System.out.println("\n\n\n\nfrom face  cube"+ccc);
     }
       else if(key.equals("Area"))
     {
       Testcode_interface ttt = new Testcode_implementation();
       ccc = ttt.areatest(location,filename,language,ofilename);
       System.out.println("\n\n\n\nfrom face  area "+ccc);
     }
       else if(key.equals("Velocity"))
     {
       Testcode_interface ttt = new Testcode_implementation();
       ccc = ttt.velocitytest(location,filename,language,ofilename);
       System.out.println("\n\n\n\nfrom face  velocity "+ccc);
     }
       else if(key.equals("Length"))
     {
       Testcode_interface ttt = new Testcode_implementation();
       ccc = ttt.lengthtest(location,filename,language,ofilename);
       System.out.println("\n\n\n\nfrom face  length "+ccc);
     }
     else
    {
      System.out.println("no input");
     }
     
     try
     {
   if(ccc==3)
   {
    update = sts3.executeUpdate("update mdl_question_attempt_steps set state='mangrright',fraction = 1 where userid ="+userid.get(x)+" and questionattemptid="+attemptid.get(z)+" and state='needsgrading';");
     System.out.println(""+update);
   }
   else
   {
    update = sts3.executeUpdate("update mdl_question_attempt_steps set state='mangrwrong', fraction = 0 where userid ="+userid.get(x)+" and questionattemptid="+attemptid.get(z)+" and state='needsgrading';");
    System.out.println(""+update);
   }
   }catch(Exception e3){System.out.println("e3 :"+e3);}
   
  // if(key.equals("SumCheck"))
    //{
    
    //}
    }
     
   try
    {
    System.out.println(attemptid2.get(0)+"  "+attemptid2.get((attemptid2.size())-1)+"  "+attemptid2.size());
    ResultSet rs3 = sts4.executeQuery("select sum(fraction), avg(fraction) from mdl_question_attempt_steps where userid ="+ userid.get(x)+" and questionattemptid between "+attemptid2.get(0)+" and "+attemptid2.get((attemptid2.size())-1)+";");
    
    while(rs3.next())
    {
     sumgrades = Double.parseDouble(rs3.getString(1));
     average = Double.parseDouble(rs3.getString(2));
     average=sumgrades/attemptid2.size();
    }
System.out.println("  pp"+sumgrades+average);
  average = average*100;
   }catch(Exception e4){System.out.println("e4 :"+e4);}
   
   try
   {
   update = sts6.executeUpdate("update mdl_quiz_attempts set sumgrades = "+sumgrades+" where quiz ="+quizid+" and userid ="+userid.get(x)+"");
    
   System.out.println("the1"+update);   
   }catch(Exception e5){System.out.println("e5 :"+e5);}
   
   try
   {
   PreparedStatement ps = con.prepareStatement("insert into mdl_quiz_grades (id,quiz,userid,grade,timemodified) values (?,?,?,?,?);");
  ps.setInt(1,0); 
   ps.setInt(2,quizid);
   ps.setInt(3,userid.get(x));
   ps.setDouble(4,average);
   ps.setInt(5,0);
   update= ps.executeUpdate(); 
   System.out.println("the"+update);
/*
 PreparedStatement ps2 = con.prepareStatement("insert into mdl_grade_grades (itemid,userid,finalgrade) values (?,?,?);"); 
 ps2.setInt(1,item);
 ps2.setInt(2,userid.get(x));
 ps2.setDouble(3,average);
 update = ps2.executeUpdate();
*/

update =  sts7.executeUpdate("update mdl_grade_grades set finalgrade ="+average+" where  userid="+userid.get(x)+" and itemid="+item+";");
    System.out.println("the2"+update);
   sumgrades=0;average=0;   
  }catch(Exception e6){System.out.println("e6 :"+e6);}	
   
        attemptid2.clear();   
        attemptid.clear();
 	 questionid.clear();
    	 behaviour.clear();
    	 answer.clear();
   	 keyword.clear();
   
 	
   }
   
   
    }catch(Exception e){System.out.println(e);}
    }catch(Exception emain){System.out.println("Error: no arguments");}  
  }
 
}

