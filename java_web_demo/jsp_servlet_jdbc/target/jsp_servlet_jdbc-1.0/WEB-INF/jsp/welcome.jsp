<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>

<h3>Welcome</h3>
<p>
    <a href="${BASE}/logout">Logout</a>
</p>

<h4>Task List</h4>
<c:choose>
    <c:when test="${not empty taskList}">
        <table>
            <c:forEach var="task" items="${taskList}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>
                        <input type="text" value="${task.todo}" class="ext-todo" style="width: 500px;"/>
                    </td>
                    <td class="ext-action">
                        <a href="#" class="ext-update_task" data-id="${task.id}">Update</a>
                        <a href="#" class="ext-delete_task" data-id="${task.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p>No Task</p>
    </c:otherwise>
</c:choose>
<p>
    <a href="#" id="create_task">Create Task</a>
</p>

<form id="update_form" action="${BASE}/task_update" method="post">
    <input type="hidden" name="id"/>
    <input type="hidden" name="todo"/>
</form>

<script type="text/javascript" src="${BASE}/www/js/jquery.min.js"></script>
<script type="text/javascript" src="${BASE}/www/js/jquery.form.min.js"></script>
<script type="text/javascript" src="${BASE}/www/js/global.js"></script>
<script type="text/javascript">
    $(function() {
        // 显示或隐藏操作按钮
        $('.ext-action').hide();
        $('.ext-todo')
            .focus(function() {
                $('.ext-action').stop().hide();
                $(this).closest('tr').find('.ext-action').show();
            }).blur(function() {
                $(this).closest('tr').find('.ext-action').fadeOut();
            });

        // 创建 Task
        $('#create_task').click(function() {
            location.href = BASE + '/task_create';
            return false;
        });

        // 更新 Task
        $('.ext-update_task').click(function() {
            var id = $(this).data('id');
            var todo = $(this).closest('tr').find('input.ext-todo').val();
            var $form = $('#update_form');
            $form.find('input[name="id"]').val(id);
            $form.find('input[name="todo"]').val(todo);
            $form.submit();
            return false;
        });

        // 删除 Task
        $('.ext-delete_task').click(function() {
            var id = $(this).data('id');
            location.href = BASE + '/task_delete?id=' + id;
            return false;
        });
    });
</script>

</body>
</html>