package {
	import flash.display.Sprite;

	public class AS_0300_Statements extends Sprite
	{
		public function AS_0300_Statements()
		{
			var v1:Array = ["a", "b", "c", "d"];
			for(var i:String in v1) {
				//trace(i);
				trace(v1[i]);
			}
			
			for each (var propertyValue:String in v1) {
				trace(propertyValue);
			}
			
			var v2:Object = {id:1, name:'lisi', age:19};
			for(var i in v2) {
				trace(i);
				trace(v2[i]);
			}
			
			for each (var propertyValue in v2) {
				trace(propertyValue);
			}
			
			trace(v2["name"]);
		}
	}
}
