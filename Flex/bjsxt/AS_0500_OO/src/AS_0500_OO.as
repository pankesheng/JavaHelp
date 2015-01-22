package {
	import com.bjsxt.as3.IFlyable;
	import com.bjsxt.as3.Student;
	import com.bjsxt.as3.T;
	import com.bjsxt.as3.Teacher;
	
	import flash.display.Sprite;
	public class AS_0500_OO extends Sprite
	{
		public function AS_0500_OO()
		{
			var s:Student = new Student();
			trace(s.name);
			
			var t:Teacher = new Teacher();
			t.f = function() {
				trace("f");
			}
			t.f();
			delete t.f;
			
			var f:IFlyable = new T();
			//t.f();
			
		}
	}
}
