<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
  <form id="form3" method="post" action="">
    <div>
      <table width="100%">
        <tr>
          <td>
            <table>
              <tr>
                <td><select id="row4Page" data-ns="" onchange="chgRow4Page(this)" name="row4Page" class="form-control">
                    <option selected value='10'>10</option>
                    <option value='20'>20</option>
                    <option value='30'>30</option>
                    <option value='40'>40</option>
                    <option value='50'>50</option>
                </select>
                </td>
                <td>
                  <ul id="pagination" class="pagination pagination-sm">

                  </ul>
                </td>
                <td><input type="text" id="tzpagenum" class="pagination-input-sm" />
                </td>
                <td>
                  <button id="tiaozhuan" name="" type="button" data-ns="" onclick="tiaoZhuan(this)" class="btn btn-default btn-sm">跳转</button>
                </td>
              </tr>
            </table>
          </td>
          <td align="right"><span id="allrowmsg" class="pull-right"></span>
          </td>
        </tr>
      </table>
    </div>
    <INPUT id='allPage' value='' type='hidden' /> <INPUT id='nowPage' value='' type='hidden' name='nowPage' /> <INPUT id='allRows' value='' type='hidden' />
  </form>
</body>
</html>