package {
	import flash.display.Sprite;
	import flash.errors.EOFError;

	public class AS_0800_ExceptionHandling extends Sprite
	{
		public function AS_0800_ExceptionHandling()
		{
			var a :Array = [1, 2, 3];
			try {
				throw new EOFError("error occur");
			} catch (error:EOFError) {
				trace(error);
			} finally {
				trace("finally");
			}
			
		}
	}
	
	
}
