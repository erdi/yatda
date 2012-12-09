<% def todos = request.todos %>
<% if (todos) { %>
	<ul class="nav nav-tabs nav-stacked">
		<% todos.each { %>
			<li class="todo">
				<form action="/todo/remove/${it.key.id}" method="post">
					${it.text}
					<button type="submit" class="btn btn-danger pull-right"><i class="icon-minus-sign icon-white"></i></button>
				</form>
			</li>
		<% } %>
	</ul>
<% } else { %>
    <h2>You have no todos yet, please add some!</h2>
<% } %>

<form action="/todo/add" method="post">
	<div class="input-append">
		<input class="input-block-level" name="text" type="text" placeholder="New Todo">
		<button class="btn btn-success" type="submit"><i class="icon-plus-sign icon-white"></i></button>
	</div>
</form>