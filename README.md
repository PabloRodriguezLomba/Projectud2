# Supuesto

Una aplicación para el juego de Animal Crossing: New Horizons donde se puede consultar informacion sobre los Peces , Fosiles y Bichos que puedes conseguir en el juego. .

Estos son los diagramas ER/CrowsFoot de la BD.

![eer](/Documentacion/eer.png)

![cfn](/Documentacion/cfm.png)

# Manual Tecnico para desarrolladores

Para empezar esta manual tecnico lo primero vamos a ver la estructura del proyecto

![estructura](/Documentacion/estructura.png)


Este proyecto utiliza una base de datos echa en mysql pare interatuar con esta utilizamos un jdbc y para simplificar el uso de este tenemos una clase dao con todos los metodos relacionados con la base de datos.

![conexion](/Documentacion/conexion.png)

este es uno de los metodos principales y posiblemente el mas importante con este se establece conexion con la base de datos y devuelve este este objecto Connection.

Este objeto Connection es un atributo en  las caleses controlladores y es el que usan para poder utilizar el resto de metodos del dao.

los metodos del dao de esta aplicacion cumplen 3 funciones lectura, escritura y eliminacion en la base de datos.

![getall](/Documentacion/getall.png)

Este metodo del dao devuelve un arraylist con todas las filas de la tabla fish, primero este metodo exigue un atributo Connection para ser ejecutado y despues hace una consulta a la base de datos y con los datos de la consulta crea objetos tipo fish que introduce en un arraylist que retorna.

Existen otros 2 metodos iguales a este pero cada uno para una tabla diferente.

![shadow](/Documentacion/getfishbyshadow.png)

Otro de los metodos del dao nos permiten obtener un fila o filas de la una tabla utilizando un dato de las columnas de la tabla , es decir en este caso utilizando una sombra el cual es un dato que es una columna en la tabla fish podemos obtener las filas que con el mismo shadow que el dato que introducimos , este metodo devuelve un arraylist porque hay filas con la misma shadow.

Existe un metodo parecido para cada columna de la base de datos.

![savedao](/Documentacion/savedao.png)

Este metodo se utiliza para guarda una fila en la base de datos , en este caso un objeto fossil este metodo recibe una conxion y un objeto fossil estrae los datos del objeto fossil y despues los introduce en la tabla.

Existen metodos parecidos para todas las tablas de la base de datos.


![deleteby](/Documentacion/deleteBy.png)

Este es el ultimo tipo de metodo del dao que se encarga de eliminar filas de las tablas , se le pasa un dato este se busca en la columna de la tabla en este caso serian las id de la tabla bug y si encuentra ese datos se elimina.



Ahora vamos a tratar unao de los tres controlladores que utilizo para cada escena de javafx ya que el codigo 
es muy parecido entre estos.

![initi](/Documentacion/initialize.png)

todos los controlladores implementa inicializable por lo que todos tienen un metodo initialize
en estos suelo darle el cell value a las columnas de la tabla y añado los filtros al file Chooser aparte de añadir los objetos de la combobox.
Esto solo cambia en el controlador de la pagina principal.

ahora vamos a ver los metodo de los controlladores.

![metodo1](/Documentacion/getallbug.png)

Este metodo simplemente utiliza un metodo del dao para obtener todos los bichos en un arraylist y le pasa este arraylist a la tabla, esta con el metodo addAll añade todos los objetos de la lista a la tabla.

![getcolumn](/Documentacion/getbycolumn.png)

Este metodo es el que se ejecuta cuando se busca un objeto o objetos de la base de datos mediante un input que introduce el usuario, lo primero que hace es comprobar que el combobox que se utiliza para selecionar el metodo de busqueda no este bacio / no se a selecionado nada, despues obtiene el valor de la combobox y lo compara para saber el parametro de busqueda ,una vez encontrado se llama a un metodo especifico que busca el objeto gracias a un metodo del dao y lo escribe en la tabla


![getone](/Documentacion/getbugbyid.png)

este es uno de los metodos que se utilizan en getBugbyColumn , este simplemente obtiene una id que el usuario introduce en un textfield , comprueba de que este textfield no este vacio y en este caso,  donde la id es de tipo int, se comprueba que solo se introduce numeros , una vez que se compruebe esto se llama a un metodo del dao que devuelve un objeto de tipo bug que se añade a la tabla.

el resto de los metodos de getBugbyColumn son identicos al ultimo metodo ,vamos ahora a ver el metodo de delete el cual utiliza tambien el textfield donde el usuario introduce un input que corresponde a una de las columnas de la tabla.

![delete](/Documentacion/delete.png)

Este metodo es muy parecido a getBugbyColumn simplemente cambia los metodos que invoca desde el dao ahora simplemente borrara filas de la tabla de la base de datos utilizando el input del usuario para encontrar las filas que borrar.

El ultimo metodo que se deveria nombrar es el que se encarga de hacer la tercera accion crud de la aplicacion hemos visto tanto busqueda como elimicion en la tabla y este se encarga de añadir filas.

![add](/Documentacion/add%20part%20one.png)
![ad](/Documentacion/addmid.png)
![addd](/Documentacion/add%20part%202.png)

en este metodo primero comprobamos que ninguno de los campos de texto esten vacios,despues se comprueba que en los campos que necesitan numeros decimales no se encuentre ningun caracter despues de eso se obtienen todos los valores que introduce el usuario y se crea un objeto de en este case tipo Bug, este objeto es introducio en un metodo de la clase dao que lo añade a la base de datos.

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

Luego escribo en este documento la informacion , el metodo lee la tabla y dependiendo de si el usuario tiene la tabla llena o solo una entrada en la tabla obtiene los datos , mediante metodos del dao y lo escribe en el documento.


y con con esto explico el codigo mas complejo de mi projecto el resto de los metodos estan comentados y son sencillos de entender.



















# Guia de Usuarios

Al ejecutar la aplicacion te encontraras en la pagina de inicio desde aqui podras acceder a una de las tres ventanas donde consigues la informacion.

Esta seria la pagina de inicio en la izquierda tienes un menu con tres botones, un pez , un fossil y un bicho puedes clickar sobre ellos directamente pero tambien puedes clicar en el icono que esta directamente encima para desplegar todo el menu.

![pagina de inicio](/Documentacion/HomePage.png)

![Menu](/Documentacion/Menu.png)
Entremos por ejemplo en bug al hacer click sobre la palabra o el icono nos llevara a una ventana simple,
utilizando los botones se escribe informocion en la tabla , tienes la opcion de por ejemplo obtener todos los bichos en este caso o obtener bichos especificos con el campo de texto y boton en la parte de abajo de la ventana.
En la parte de arriva hay una barra en verde con las siguientes opciones una flecha para poder volver a la pagina de inicio y un icono de un documento este es utilizado para exportar la informacion de la tabla a texto .

![Fish](/Documentacion/bug.png)

![Bug](/Documentacion/All%20bug.png)

![BugAll](/Documentacion/One%20bug.png)

![part2](/Documentacion/One%20bug%202.png)

la siguiente opcion seria introducir datos en los campos de texto que se encuentran en la mitad de la ventana, despues de introducir esos datos clickas en el boton add y se añadiran los datos a la base de datos.
![shu](/Documentacion/addbicho.png)

![loco](/Documentacion/result%20bicho.png)

Ahora vamos a probar a exporta para esto hacemos click en el icono del documento que esta arriba a la  izquierda, despues de esto aparecera otra ventana donde podras selecionar donde guardar y en que formato.

![Export](/Documentacion/Export.png)

![ExportOptions](/Documentacion/Export%20options.png)



# Propuestas de mejora

 Las propuestas de mejora que veo serian la creacion de otra ventana donde se pueda añadir desde esta objetos a la tabla esto devido a que los campos de texto que se utilizan para añadir filas a la base de datos ocupan mucho espacio  y la ventana acaba pareciendo que esta todo apretado en un lugar.
 
Otra opcion seria conseguir que se pueda arrastrar la aplicacion y que se pueda exportar la informacion en mas formatos.

# Opinion 

Bueno contento con el final si que es verdad que puede mejorar unos aspectos pero estoy satisfecho con el resultado , espero tener la misma cualificacion que el anterior pero si que  es verdad que no lo noto tan pulido como el proyecto de la unidad 1.