<html>
DONE!!!
<?php

require_once("config.php");
require_login();
$PAGE->set_title('Auto-Check');
$site=get_site();
$loginsite='Auto-Check';
$PAGE->set_heading($site->fullname);
$PAGE->navbar->add($loginsite);
echo $OUTPUT -> header();


 $option = $_REQUEST["quiz"];
 $quizno = null;
 if($option=="c")
{
  $quizno="11";
}
else if($option=="cpp")
{
 $quizno="12";
}
else if($option=="java")
{
 $quizno="13";
}
echo $option . $quizno;
 $output = "";
 $read_console=null;
 $ret=999;
 
 $run= "java Autocheck ".$quizno." ".$option;
 try
 {
 exec("cd /home/ttt/ppp/testingc/testing-version-2/; ".$run,$read_console,$ret);
  }catch(Exception $e){echo $e;}
echo $read_console[0]."</br>".$ret;
for($i=0;$i<=count($read_console);$i++)
{
echo $read_console[$i]."</br>";
}
echo $OUTPUT -> footer();
?>
</html>
