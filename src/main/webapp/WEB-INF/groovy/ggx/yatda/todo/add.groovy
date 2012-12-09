package ggx.yatda.todo

import com.google.appengine.api.datastore.Entity

def properties = params.subMap(['text']) + [userId: user.userId]
def todo = new Entity('todo') << properties
todo.save()

forward '/ggx/yatda/main.groovy'