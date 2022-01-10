const toggleSidebar=()=>{
	
	if($(".sidebar").is(":visible")){
		// true
		// Close the sidebar
		$(".sidebar").css("display","none");
		
		// Shift our content to left by removing margin-left
		$(".content").css("margin-left","0%");
	}
	
	else{
		//false
		// Show the sidebar
		$(".sidebar").css("display","block");
		
		// Shift our content to right by adding margin-left
		$(".content").css("margin-left","20%");
		
	}
};