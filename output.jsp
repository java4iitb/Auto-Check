<html>
DONE!!!
<%@ page import="java.lang.*"%>
<%@ page import="java.io.*"%>
<%
 String option = request.getParameter("quiz");
 String quizno = null;
 if(option.equals("c"))
{
  quizno="11";
}
else if(option.equals("cpp"))
{
 quizno="12";
}
else if(option.equals("java"))
{
 quizno="13";
}
 String output = "";
 String read_console=null;
 String filepath = "/home/ttt/ppp/testingc/testing-version-2/";
 File f = new File(filepath);
 String run= "java Autocheck "+quizno+" "+option;
 Process p = Runtime.getRuntime().exec(run,null,f);
 try
 {
  p.waitFor();
 }catch(Exception e){}

 BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
 while((read_console=br.readLine())!=null)
{
  output= output+"</br>"+read_console;
}
%>
<%=output%>
</html>
