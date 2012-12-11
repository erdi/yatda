<% def todos = request.todos %>
<% if (todos) { %>
	<ul class="nav nav-tabs nav-stacked">
		<% todos.each { %>
			<li class="todo">${it.text}</li>
		<% } %>
	</ul>
<% } else { %>
    <h2>You have no todos yet, please add some!</h2>
<% } %>