import java.io.PrintWriter;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.examples.quickstart.entity.User;

import com.zcj.web.dto.ServiceResult;


public class UserAction {
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public void register(String username, String password, PrintWriter out) {
		out.write(userService.insert(username, password));
	}
	
	@RequestMapping("/tolist/{catalogId}")
	public String tolist(@PathVariable Long catalogId, Model model) {
		model.addAttribute("catalog", catalogService.findById(catalogId));
		return "admin/article/article_list.ftl";
	}
	
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(User user, RedirectAttributes redirectAttributes) {
		// TODO ...
		
		// 对请求的重定向生效之前被临时存储（通常是在session)中，并且在重定向之后被立即移除，相当于只在重定向的请求方法里的Model中加入此属性和值。
		redirectAttributes.addFlashAttribute("message", "更新用户" + user.getLoginName() + "成功");
		
		// 重定向到某请求，客户端URL改变，可解决F5多次提交问题
		return "redirect:/admin/user";
	}
	
}