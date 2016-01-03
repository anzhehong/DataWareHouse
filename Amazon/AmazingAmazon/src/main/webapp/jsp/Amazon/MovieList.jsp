<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fowafolo
  Date: 16/1/2
  Time: 下午10:28
  To change this template use File | Settings | File Templates.
--%>




<style type="text/css">

  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  caption {
    text-align: left;
    font-size: 1.5em;
    color: seagreen;
    font-weight: bold;
    text-transform: uppercase;
    padding: 5px;
  }

  table {
    color: #333;
    font-family: sans-serif;
    font-size: .9em;
    font-weight: 300;
    line-height: 40px;
    border: 2px solid #428bca;
    width: 800px;
    margin: 20px auto;
  }

  thead tr:first-child {
    background: #428bca;
    color: #fff;
    font-weight: bold;
    text-align: left;
    text-transform: uppercase;
  }

  th {padding: 0 15px 0 20px;}
  td:first-child {padding-left: 20px;}

  thead tr:last-child th {border-bottom: 3px solid #ddd;}

  tbody tr:hover {background-color: #f0fbff;}
  tbody tr:last-child td {border: none;}
  tbody td {
    text-align: left;
    border-bottom: 2px solid #ddd;}

  td:nth-child(3),
  td:nth-child(4),
  td:nth-child(6){
    text-align: center;
  }

  .link {
    color: #428bca;
    text-align: center;
    text-decoration: none;
    padding-left: 15px;
  }

  .link:hover {
    text-decoration: underline;
    cursor: pointer;
  }
</style>
<html>
<title>Amazon Search Result</title>
<body>

<table>
  <caption>Search Result</caption>
</table>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:400px"></div>
<!-- ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">
  // 路径配置
  require.config({
    paths: {
      echarts: 'http://echarts.baidu.com/build/dist'
    }
  });

  // 使用
  require(
          [
            'echarts',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
          ],
          function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main'));

            var option = {
              tooltip: {
                show: true
              },
              legend: {
                data:['Movie']
              },
              xAxis : [
                {
                  type : 'category',
                  data : ["Hive","MySQL"]
                }
              ],
              yAxis : [
                {
                  type : 'value'
                }
              ],
              series : [
                {
                  "name":"Time",
                  "type":"bar",
                  "data":[100,200]
                }
              ]
            };

            // 为echarts对象加载数据
            myChart.setOption(option);
          }
  );
</script>



<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="bugetMain" style="height:400px"></div>
<!-- ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">
  // 路径配置
  require.config({
    paths: {
      echarts: 'http://echarts.baidu.com/build/dist'
    }
  });

  // 使用
  require(
          [
            'echarts',
            'echarts/chart/gauge' // 使用柱状图就加载bar模块，按需加载
          ],
          function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('bugetMain'));

            var option = {
              legend: {
                data:['Movie']
              },
              xAxis : [
                {
                  type : 'category',
                  data : ["Hive","MySQL"]
                }
              ],
              yAxis : [
                {
                  type : 'value'
                }
              ],
              tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
              },
              toolbox: {
                show : true,
                feature : {
                  mark : {show: true},
                  restore : {show: true},
                  saveAsImage : {show: true}
                }
              },
              series : [
                {
                  name:'Execution Time',
                  type:'gauge',
                  detail : {formatter:'{value}%'},
                  data:[{value: 33, name: 'MySQL/Hive'}]
                }
              ]
            };

            // 为echarts对象加载数据
            myChart.setOption(option);
          }
  );
</script>




<table>
  <caption>Search Result</caption>
  <thead>
  <tr>
    <th>MovieID</th>
    <th>ProductId</th>
    <th>Name</th>
    <th>Time</th>
    <th>Version</th>
    <th>Style</th>
  </tr>
  </thead>
  <tbody>


  <c:forEach items="${result}" var="result">
    <tr>
      <td>${result.movieId}</td>
      <td>${result.productId}</td>
      <td>${result.movieName}</td>
      <td>time</td>
      <td>${result.movieVersion}</td>
      <td>${result.style}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>


</html>

