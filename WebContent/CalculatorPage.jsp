<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form name = 'calculate' action="Calculator" method ="post">
<h1>Text Based Calculator</h1>
<p>
    <label>Equation</label>
    <input type ="text" style="font-size:10pt;height:20px;width:400px;"
           name ="textEquation"/>
    <input type="submit" style="height:25px;width:200px" value="Calculate">
</p>
<label>Answer</label>
<input type = "text" style="font-size:10pt;height:20px;width:400px;"
       name = "textAnswer" value="${requestScope.answer}"/>
</body>
</html>