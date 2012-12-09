get '/', forward: '/ggx/yatda/main.groovy'

post '/todo/remove/@id', forward: '/ggx/yatda/todo/remove.groovy?id=@id'
post '/todo/add', forward: '/ggx/yatda/todo/add.groovy'