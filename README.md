# Supuesto

Una aplicacion para el juego de Animal Crossing: New Horizons donde se puede consultar informacion sobre los Peces , Fosiles y Bichos que puedes conseguir en el juego.
Utilizamos La Api de acnhapi.

Aqui esta la coleccion que cree en postman junto con 3 request a la api:

![request1](/Documentacion/cap1.png)


![request2](/Documentacion/cap2.png)


![request3](/Documentacion/Cap3.png)


# Manual Tecnico para desarrolladores

Para empezar esta manual tecnico lo primero vamos a ver la estructura del proyecto

![estructura](/Documentacion/Estructura%20de%20proyecto.png)

Ahora vamos a tratar unao de los tres controlladores que utilizo para acceder a la api ya que el codigo 
es muy parecido entre estos.

![codigo1](/Documentacion/Initialize.png)

todos los controlladores implementa inicializable por lo que todos tienen un metodo initialize
en estos suelo darle el cell value a las columnas de la tabla y añado los filtros al file Chooser.
Esto solo cambia en el controlador de la pagina principal.

ahora vamos a ver los metodo de los controlladores.

![metodo1](/Documentacion/getAll.png)

Este metodo simplemente manda una peticion get a la api obtiene el resultado , utilizando un ObjectMapper convertimos el json que nos da la api en clases java que forman parte de un array luego este se lo pasamos a la table view y esta lo procesa internamente y lo escribe en la tabla.

![getOne1](/Documentacion/get%20one%20first%20part.png)

Este es el metodo que se utiliza para conseguir un solo objeto para esto el usuario insertara un id , lo primero que hacemos es comprobar que no este vacio el texto si lo esta no el metodo hace absolutament nada, si el campo de texto no esta vacio entonces mediante un patter y un matcher se comprueba que es un decimal devio  a que en java fx no se puede hacer un text field que solo admita decimales.

![getOne2](/Documentacion/get%20one%202%20part.png)

Una vez que se comprueba que la id es valida se ejecuta un codigo muy parecido al metodo anterior simplemente que este no obtiene un arrya sino un objeto que se añade a la tabla, antes de todo eso hay que remarcar que se borra todos los  contenidos de la tabla.

Vamos ahora  a hablar sobre el controllador de la pagina de inicio , lo unico destacable es el menu que se crea dentro del metodo initialize , este se crea utilizando  transitions y fade transitions.

![menucode](/Documentacion/MenuIni%20part1.png)

![menucode2](/Documentacion/menu%20part2.png)

Esto parece dificil en codigo pero es bastante sencillo explicarlo, lo que hace es crear 2 transition una fade y otro translate y lo que hace al principio cuando abres la aplicaciones colocar el menu a unos 600 pixeles hacia la izquirda,
lo que hace que no sea visible y despues con otra transicion cuando clickes el boton que llama al menu lo pone en la posicion que le corresponde ,
por lo cual simplemente mueves el menu desde una posicion fuera de pantalla a otra que sea visible y esto en bucle .


Por ultimo el metodo que utilizo para exportar los datos , en este metodo exporto los datos de los objetos que se encuentran en la tabla, tambien utilizo el objeto fileChooser.

![save](/Documentacion/Save.png)

Empezemos lo primero para poder utilizar el fileChooser necesitamos la ventana que se puede conseguir desde cualquier widget o objeto de javafx como un botton en este caso yo utilize la tablaView.

Despues le doy un titulo y seleciono el nombre por defecto de los documentos a continuacion utilizo el metodo showSaveDialog de FileChooser utilizando como atributo la ventana ,este metodo devuelve un documento de la clase File y con este creo un documento.

Luego escribo en este documento la informacion , el metodo le la tabla y dependiendo de si el usuario tiene la tabla llena o solo una entrada en la tabla obtiene los datos , hace una request a la api y lo escribe en el documento.


y con con esto explico el codigo mas complejo de mi projecto el resto de los metodos estan comentados y son sencillos de entender



















# Guia de Usuarios

Al ejecutar la aplicacion te encontraras en la pagina de inicio desde aqui podras acceder a una de las tres ventanas donde consigues la informacion.

Esta seria la pagina de inicio en la izquierda tienes un menu con tres botones, un pez , un fossil y un bicho puedes clickar sobre ellos directamente pero tambien puedes clicar en el icono que esta directamente encima para desplegar todo el menu.

![pagina de inicio](/Documentacion/HomePage.png)

![Menu](/Documentacion/Menu.png)
Entremos por ejemplo en fish al hacer click sobre la palabra o el icono nos llevara a una ventana simple con una tabla y dos botones,
utilizando estos botones se escribe informocion en la tabla tienes la opcion de por ejemplo obtener todos los peces en este caso o solo uno utilizando la id.
En la parte de arriva hay una barra en verde con las siguientes opciones una flecha para poder volver a la pagina de inicio y un icono de un documento este es utilizado para exportar la informacion de la tabla a texto o json.

![Fish](/Documentacion/Fish.png)

![Bug](/Documentacion/One%20Bug.png)

![BugAll](/Documentacion/All%20bug.png)

La ventana de fosiles es un poco diferente a las demas pues necesita el nombre del fossil para poder conseguir informacion en vez de una id.

![Fossil](/Documentacion/Fossil.png)

Ahora vamos a probar a exporta para esto hacemos click en el icono del documento que esta arriba a la  izquierda, despues de esto aparecera otra ventana donde podras selecionar donde guardar y en que formato.

![Export](/Documentacion/Export.png)

![ExportOptions](/Documentacion/Export%20options.png)



# Propuestas de mejora

 Las propuestas de mejora que veo serian la creacion de otra ventana con informacion mas detallada de los objetos ya sea pezes , fossiles o bichos y a esta la accederias clickando sobre el objeto en la tabla.
 
Otra opcion seria conseguir que se pueda arrastrar la aplicacion y que se pueda exportar la informacion en mas formatos.

# Opinion 

Bastante contento con el trabajo y el producto final , no consumio tanto tiempo como pense de echo fui a una marcha bastante relajada y aun asi me sobraron dias, mi cualificacion estimada sera sobre el 7 mas o menos.