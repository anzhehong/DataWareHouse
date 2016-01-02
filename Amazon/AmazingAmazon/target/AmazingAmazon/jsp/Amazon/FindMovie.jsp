<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fowafolo
  Date: 16/1/2
  Time: 下午10:27
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<style type="text/css">
  fieldset
  {
    border: 2px solid #428bca;
    margin:5px;
    padding:20px;
    background:white;
  }
  legend
  {
    font-size:1em;
    color:seagreen;
  }

  form {
    text-align: left;
    font-family: sans-serif;
    margin:20px auto;
    width:800px;
  }
</style>

<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>Amazing Amazon Center</title>
  <link rel="stylesheet" type="text/css" href="/Amazon/rs/jquery.datetimepicker.css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="/Amazon/rs/jquery.datetimepicker.js"></script>
  <script src="/Amazon/rs/./jquery.js"></script>
  <script src="/Amazon/rs/jquery.datetimepicker.full.js"></script>
</head>

<script>
  $(function(){$.datetimepicker.setLocale('en');
    $('#datetimepicker_format').datetimepicker({value:'2015/04/15 05:03', format: $("#datetimepicker_format_value").val()});
    $("#datetimepicker_format_change").on("click", function(e){
      $("#datetimepicker_format").data('xdsoft_datetimepicker').setOptions({format: $("#datetimepicker_format_value").val()});
    });
    $("#datetimepicker_format_locale").on("change", function(e){
      $.datetimepicker.setLocale($(e.currentTarget).val());
    });

    $('#datetimepicker').datetimepicker({
      dayOfWeekStart : 1,
      lang:'en',
      disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
      startDate:  '1986/01/05'
    });
    $('#datetimepicker').datetimepicker({value:'2015/04/15 05:03',step:10});
    $('.some_class').datetimepicker();
    $('#default_datetimepicker').datetimepicker({
      formatTime:'H:i',
      formatDate:'d.m.Y',
      //defaultDate:'8.12.1986', // it's my birthday
      defaultDate:'+03.01.1970', // it's my birthday
      defaultTime:'10:00',
      timepickerScrollbar:false
    });
    $('#datetimepicker_dark').datetimepicker({theme:'dark'})})
</script>









<body>


<form method="post" name="FindByMovie">
  <fieldset>
    <legend><b>Find By Movie Info</b></legend>
    <label>According to Movie Name:</label>
    <input type="text" name="moviename" id="moviename" size="30" placeholder="input movie name">
    <input type="submit" value="Search" formaction="/Amazon/searchByMovie/searchByName">
  </fieldset>
  <fieldset>
    <label>According to Movie Style:</label>
    <input type="text" name="style" size="30" placeholder="input movie style">
    <input type="submit" value="Search">
  </fieldset>
  <fieldset>
    <label>According to Movie Starring:</label>
    <input type="text" name="starring" size="30" placeholder="input movie starring">
    <input type="submit" value="Search" formaction="/Amazon/searchByMovie">
  </fieldset>
  <fieldset>
    <label>According to Movie Actor:</label>
    <input type="text" name="actor" size="30" placeholder="input movie actor">
    <input type="submit" value="Search" formaction="/Amazon/searchByMovie">
  </fieldset>
  <fieldset>
    <label>According to Movie Director:</label>
    <input type="text" name="director" size="30" placeholder="input movie director">
    <input type="submit" value="Search" formaction="/Amazon/searchByMovie">
  </fieldset>
  <fieldset>
    <label>According to Movie Version:</label>
    <input type="text" name="version" size="30" placeholder="input movie version">
    <input type="submit" value="Search" formaction="/Amazon/searchByMovie">
  </fieldset>
  <fieldset>
    <label>According to Movie Time, Year:</label>
    <input type="text" value="" id="datetimepicker" name="date"/>
    <input type="submit" value="Search" formaction="/Amazon/searchByMovie/searchByDate">
    </select>
  </fieldset>
  <fieldset>
    <label >Search by Mutiple Conditions</label>
    <input type="submit" value="Search" formaction="/Amazon/searchByMovie">
  </fieldset>
</form>

<form method="post" name="FindByReview" action="">
  <fieldset>
    <legend><b>Find By Review Score</b></legend>
    <!-- <label>According to Movie Name:</label>
    <input type="text" name="movieName" size="30" placeholder="input movie name">
    <input type="submit" value="Search"> -->
  </fieldset>
</form>


</body>
</html>
