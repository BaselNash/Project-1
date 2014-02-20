
 
/*

The map for the 2D array

[6]	[7]	[8]			N

[3]	[4]	[5]		W		E

[0]	[1]	[2]			S

*/
 
//2D array, currentLocation (starting point of the game), score, player Inventory, Location/items prorotypes are initialized here,these are all global elements or variables.
 	var nav = [
	 //	 N	S  E  W
		[3, -1, 1, -1],
		[4, -1, 2, 1],
		[5, -1, -1, 1],
		[6, 0, 4, -1],
		[7, 1, 5, 3],
		[8, 2, -1, 4],
		[-1, 3, 7, -1],
		[-1, 4, 8, 6],
		[-1, 5, -1, 7], 
	]
 
 	currentLocation = 0;
 
 	score = 0
 
	DisplayLocationArray = ["Gallery Room", "Hallway", "Kitchen", "Living Room", "Atrium", "Television Room", "Guest Bath Room", "Front Porch", "Winery"];
	
	PlayerInventory = [];
 
 //Array for displaying the id, name, description, items.

 	Locals = new Array();

 		Locals[0] = new Locale(0);
    	Locals[0].name = "Gallery Room";             
     	Locals[0].desc = "The room has several paintings, each of them are very beautiful."; 
 		Locals[0].items = [new Items(0)];
 		Locals[0].items[0].name = "Priceless Rose Painting";
 		Locals[0].items[0].desc = "Worth a lot of money";
	
 		Locals[1] = new Locale(1);
   		Locals[1].name = "Hallway";             
     	Locals[1].desc = "The room is a very Long Hallway, that leads to the kitchen or the gallery room."; 
 		Locals[1].items = []; 
	
 		Locals[2] = new Locale(2);
     	Locals[2].name = "Kitchen";             
     	Locals[2].desc = "The room is a beautiful kitchen with state of the art kitchenware, and delicious food"; 
 		Locals[2].items = [new Items(2)];
 		Locals[2].items[0].name = "Gold Blender";
 		Locals[2].items[0].desc = "It\'s a blender dipped in gold, shouldn't be useful but looks good though";
	
 		Locals[3] = new Locale(3);
     	Locals[3].name = "Living Room";             
     	Locals[3].desc = "The room is a Large living room, with fancy and comfy couches."; 
 		Locals[3].items = [];
	
 		Locals[4] = new Locale(4);
     	Locals[4].name = "Atrium";             
     	Locals[4].desc = "The room is the center of the house, with a beautiful wood stairs leading to the second floor."; 
 		Locals[4].items = []; 
	
 		Locals[5] = new Locale(5);
     	Locals[5].name = "Television Room";             
     	Locals[5].desc = "The room has a large screen television set, with surround sound and 3D display settings."; 
 		Locals[5].items = [new Items(5)]; 
 		Locals[5].items[0].name = "Large Flat Screen Television";
 		Locals[5].items[0].desc = "3D, LED and surround sound, what more could you want!";
	
 		Locals[6] = new Locale(6);
     	Locals[6].name = "Guest Bath Room";             
     	Locals[6].desc = "The room is an amazing bathroom with a porcelain sink and bath tub, along with gold incrusted shower handels."; 
 		Locals[6].items = [new Items(6)]; 
 		Locals[6].items[0].name = "Bar of Soap";
 		Locals[6].items[0].desc = "Incase you felt like taking a bath, here'\s a bar of Soap";
	
 		Locals[7] = new Locale(7);
     	Locals[7].name = "Front Porch";             
     	Locals[7].desc = "The room is a spacious porch, with a spectacular view."; 
 		Locals[7].items = [];
	
 		Locals[8] = new Locale(8);
     	Locals[8].name = "Winery";             
    	Locals[8].desc = "the room is a cellar filled with all types of expensive wine."; 
 		Locals[8].items = [new Items(8)];
 		Locals[8].items[0].name = "Expenisve Bottle of CHATEAU LAFITE, 1787";
 		Locals[8].items[0].desc = "worth more money than you can imagine, it would be a shame to waste good Wine.";
		
		//These function are the actions, methods and protoypyes of the game. 
 
 	function navigateTo(direction){
		
		var displayScreen = document.getElementById('displayScreen');
 	
		var NewLocation = nav[currentLocation][direction];
	
		if (NewLocation === -1) {
		
			displayScreen.value = displayScreen.value + '(Invalid Move) ';
		
			score = score - 5;
		
		}else{
		
			currentLocation = NewLocation;
		
			score = score + 5;
		}
 	};
 
 	function takeItem(){
		
		var displayScreen = document.getElementById('displayScreen');
	 
		var emptyItems = Locals[currentLocation].items;
	 
		 if(emptyItems.length === 0 ){
	 	
			 displayScreen.value = displayScreen.value + "There are no items to take" + '\n' + '\n';
		
	 	}else{
		 
			var element = emptyItems[0] //Locals[0].items.push(new Items(0)); //emptyItems.items[0];
	 	
			PlayerInventory.push(element);
		
			Locals[currentLocation].items = [];
		
			displayScreen.value = displayScreen.value + " Item " + element.name + " has been added to your inventory." + '\n' + '\n';
		}
 	};	
		
	function displayOptions(){
		
		var displayScreen = document.getElementById('displayScreen');
	 
		displayScreen.value = displayScreen.value + Locals[currentLocation].toString() + ' [Score = ' + score + '] ' + ' >> \n' + '\n';
		
		displayScreen.scrollTop = displayScreen.scrollHeight;
	
		if(Locals[currentLocation].toString() === undefined || Locals[currentLocation].toString() === -1){
		
			displayScreen.value = displayScreen.value + '(Invalid move)' + '\n' + '\n';
		
		}
	};
 
 	function Locale(id){
 	
	 	this.id = id;
		this.name = "";
		this.desc = "";
	 	this.items = [];
	 	this.toString = function (){
		 
		  	return "[Locale id = " + this.id + " name = " + this.name + " desc = " + this.desc + " items = " + this.items + "]";
	 	}
	 };
 
 	function Items(id){
	 
	 	this.id = id
	 	this.name = ""
	 	this.desc = ""
	 	this.toString = function (){
		 
		  	return "[Locale id = " + this.id + " name = " + this.name + " desc = " + this.desc + "]";
	 	 }
 	};
 
 	function typeNavigator(){
 	   
		button = document.getElementById('userNavigationControler').value;
		var buttonUpperCase = button.toUpperCase();
	
	
		if(buttonUpperCase === "N" || buttonUpperCase === "NORTH"){
		
			navigateTo(0);
			displayOptions();
		
		}if(buttonUpperCase === "S" || buttonUpperCase === "SOUTH"){
		
			navigateTo(1);
			displayOptions();
		
		}if(buttonUpperCase === "W" || buttonUpperCase === "WEST"){
		
			navigateTo(3);
			displayOptions();
		
		}if(buttonUpperCase === "E" || buttonUpperCase === "EAST"){
		
			navigateTo(2);
			displayOptions();
		
		}if(buttonUpperCase === "D" || buttonUpperCase === "DISPLAY"){

			categorySelection();
		
		}if(buttonUpperCase === "H" || buttonUpperCase === "HELP"){
		
			help();
		
		}if(buttonUpperCase === "C" || buttonUpperCase === "CLEAR"){
		
			clearScreen();
		
		}if(buttonUpperCase === "Q" || buttonUpperCase === "QUIT"){
		
			quitSimulation();
		
		}if(buttonUpperCase === "I" || buttonUpperCase === "INVENTORY"){
		
   	 	   DisplayInventory();
		 
   		}if(buttonUpperCase === "T" || buttonUpperCase === "TAKE"){
   		
			takeItem();
   
   		}else {
			
			//For some odd reason this text seems to break the code, and it's not doing it's intended purpose. But i'm typing it as a comment to show that I actually did it.
		
		//document.getElementById('displayScreen').value = '(This is an invalid type)' + 'Choose the appropriate text type, to see the types of commands enter \'d\',\'display\', or press the display button below' + '\n' + '\n';
		
		}
	};
	
	//these are the functions for the buttons to Display 

	function DisplayInventory(){
		
		var displayScreen = document.getElementById('displayScreen');
	
  	 	for(var i = 0; i < PlayerInventory.length; i++){
			
  		 	displayScreen.value = displayScreen.value +  i +  "-" + PlayerInventory[i] + " | " + '\n' + '\n';
		 }
	 };

	function categorySelection(){
		
		var displayScreen = document.getElementById('displayScreen');
	
	 	for(var i = 0; i < nav.length; i++){
		
		 	displayScreen.value = displayScreen.value +  i +  "-" + DisplayLocationArray[currentLocation] + " | " + '\n' + '\n';
		}	
	};

	function inti(){
		
		var displayScreen = document.getElementById('displayScreen');
	
		displayScreen.value = 'Your First location is ' + Locals[currentLocation].toString() + ' [Score = ' + score + '] ' + ' >> \n' + '\n';
	};

	function help(){
		
		var displayScreen = document.getElementById('displayScreen');
	
		displayScreen.value = displayScreen.innerHTML + 'Use the Navigation Buttons at the bottom of the page to navigate through the map.' + 
		'\n' + ' You may use the type box to type in comands:-' + 
		'\n' +  '1- w or west' + '\n' + '2- n or north' + 
		'\n' + '3- s or south' + '\n' + '4- e or east' + 
		'\n' + '5- d or display' + '\n' + '5- t or take' + 
		'\n' + '7- c or clear' + '\n' + '8- q or quit' + 
		'\n' + 'For points, everytime you enter a room you get 5 points, but everytime you make a wrong move you lose 5 points.'; +
		'\n' + 'inventory will display nothing, until you take an item'; +
		'\n' + '\n'; 
	};

	function clearScreen(){
		
		var displayScreen = document.getElementById('displayScreen');
	
		displayScreen.value = Locals[currentLocation].toString();
	};

	function quitSimulation(){
		
		var displayScreen = document.getElementById('displayScreen');
	
		displayScreen.value= 'Thank You For playing';
	
		currentLocation = 0;
	
		score = 0;
	};
