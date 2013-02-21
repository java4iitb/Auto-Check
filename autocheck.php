<html>
<head>
Hello</br>
</head>
<body>
<?php

 $a ="hello";
 echo $a;
?>

<form id="test" name="test" method="post" action="output.php">
<input type="radio" name="quiz" value="c">C</input></br>
<input type="radio" name="quiz" value="cpp">C++</input></br>
<input type="radio" name="quiz" value="java">Java</input></br>
<input type="button" name="select" value="select" onclick="validate()"></input>

</form>

<script>
function validate()
{
 alert("confirm");

 var checked = getSelectedRadio(document.forms.test.elements.quiz);
 if(checked)
 {
   alert("proper");
   (document.forms.test).submit();
 }
 else
{
  alert("Please select the quiz");
}
 
}

function getSelectedRadio(radio_name)
{
  for(var i = 0; i < radio_name.length ; i++ )
  {
     if(radio_name[i].checked)
     {
          return radio_name[i];
     }
  }
 return undefined;
}
</script>
</body>
</html>
