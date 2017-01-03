<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap table Template</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css">

  </head>
  <body>
    <div class="container">
      <div class="row">
        <table data-toggle="table"
           data-show-refresh="true"
           data-show-toggle="true"
           data-show-columns="true"
           data-show-export="true"
           data-detail-view="true"
        	   data-method="post"
               data-url="query2"
               data-pagination="true"
               data-side-pagination="server"
               data-page-list="[10, 25, 50, 100, ALL]"
               data-search="true"
               data-query-params="queryParams"
               data-height="500"
               data-striped="true">
            <thead>
              <tr>
                  <th data-field="html_id">ID</th>
                  <th data-field="docno">Docno</th>
                  <th data-field="url">URL</th>
                  <th data-field="title">Title</th>
                  <th data-field="keywords">Keywords</th>
                  <th data-field="description">Description</th>
              </tr>
            </thead>
        </table>
      </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>

    <script type="text/javascript">
    function queryParams(params) {
      console.log(JSON.stringify(params));

      // 分页请求参数
      return params;
    }
    </script>
  </body>
</html>