package com.ankit.demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.jms.Queue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@CrossOrigin(origins = "localhost:4200")
public class AlienController {
	@Autowired
	private AlienRepository ar;
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private HobbyRepo hobbyRepo;
	@Autowired
	private SkillRepo skillRepo;
	@Autowired
	private Queue queue;
	@Autowired
    private JmsTemplate jmsTemplate;
	@Autowired
	private StudentImageRepo studentImageRepo;
	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private CommonMethod commonMethod;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String rootUploadPath="C:\\D data\\UploadDocs";
	
	@GetMapping(path="/")
	public String getHome(ModelMap model) {
		return "redirect:/home";
	}
	
	@GetMapping(path="/home")
	public String addAlien(ModelMap model) {
		//ar.save(alien);
		System.out.println("Inside controller");
		model.addAttribute("studentdtBean", new StudentdtBean());
		String[] hobbies = {"Cricket","Football","Volley ball","Basket ball"};
		
		  String[] skills = new String[4]; skills[0] = "Java"; skills[1] = "Android";
		  skills[2] = "PHP"; skills[3] = "Python";
		  
		  List<Country> countries = countryRepo.findAll();
		  countries.add(0, new Country(new Long(0),"Please Select"));
		  State[] states = new State[1];
		  State st = new State();
		  st.setStateId(new Long(0));
		  st.setStateName("Please Select");
		  states[0]=st;
		model.addAttribute("hobbies", hobbies);
		City[] cities = new City[1];
		City city = new City();
		city.setCityId(new Long(0));
		city.setCityName("Please Select");
		cities[0]=city;
		model.addAttribute("cities", cities);
		 model.addAttribute("skills", skills); 
		 model.addAttribute("isEdit", false);
		 model.addAttribute("countries", countries);
		 model.addAttribute("states", states);
		return "first";
	}
	@PostMapping(path="/savestudent")
	public String saveStudent(@ModelAttribute StudentdtBean studentdtBean,RedirectAttributes redirectAttributes,ModelMap modelMap) {
		try {
		System.out.println("Inside post controller");
		List<Hobby> hobbies = new ArrayList<Hobby>();
		List<Skill> skills = new ArrayList<Skill>();
		Student s = new Student();
		s.setStudentName(studentdtBean.getStudentName());
		s.setEmailId(studentdtBean.getEmailId());
		s.setPassword(bCryptPasswordEncoder.encode(studentdtBean.getPassword()));
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateAsString = studentdtBean.getDate();
		Date date = sourceFormat.parse(dateAsString);  
		s.setDate(date);
		s.setGender(studentdtBean.getGender());
		s.setAddress(studentdtBean.getAddress());
		s.setCountry(countryRepo.getCountryNameById(Long.parseLong(new Integer(studentdtBean.getCountry()).toString())));
		Long stateId = new Long(studentdtBean.getState());
		s.setState(stateRepo.getStateNameById(stateId));
		Long cityId = new Long(studentdtBean.getCity());
		s.setCity(cityRepo.getCityNameById(cityId));
		for(String str:studentdtBean.getHobbies()) {
			Hobby h = new Hobby();
			h.setHobbyName(str);
			h.setStudent(s);
			hobbies.add(h);
			}
		for(String str:studentdtBean.getSkills()) {
			Skill sk = new Skill();
			sk.setSkillName(str);
			sk.setStudent(s);
			skills.add(sk);
			}
		studentRepo.save(s);
		hobbyRepo.saveAll(hobbies);
		skillRepo.saveAll(skills);
		//Default User role to all users
		jdbcTemplate.update(
			      "INSERT INTO users_roles VALUES (?, ?)", s.getStudentId(),2);
		MessageConfigBean messageConfigBean = new MessageConfigBean();
		int recId = Integer.parseInt(s.getStudentId().toString());
		messageConfigBean.setFromMailId("ankitraj.raj82@gmail.com");
		messageConfigBean.setRecipientId(recId);
		messageConfigBean.setRecipientName(s.getStudentName());
		messageConfigBean.setToMailId(s.getEmailId());
		messageConfigBean.setMailSubject("Welcome To Demo Application : "+s.getStudentName());
		messageConfigBean.setMailContent("<h1 style=\"color:blue;\">Hello " +s.getStudentName() +"</h1><br><p style=\"color:red;\"> Thank you for subscribing to our portal</p>");
		//jmsTemplate.convertAndSend(queue, messageConfigBean);
		redirectAttributes.addFlashAttribute("saveSuccess", "Student saved successfully");
		return "redirect:/viewStudentsmapping/1";
	}
		catch(Exception e) {
			//commonMethod.errorMethod(modelMap);
			return "errorPage";
		}
	}
	@GetMapping(path="/viewStudentsmapping/{linkNo}")
	public String ViewStudents(ModelMap model,@PathVariable("linkNo") int linkNo) {
		/*
		 * List<Student> list = studentRepo.findAll(); model.addAttribute("list",list);
		 */
		long totalLinks = 0;
		Pageable pageable = PageRequest.of(linkNo-1,10);
		Page<Student> pageDetails =  studentRepo.findAll(pageable);
		List<Student> list = pageDetails.getContent();
		long size = pageDetails.getTotalElements();
		totalLinks = size % 10 == 0 ? size/10 : size/10+1;
		model.addAttribute("currentPage", linkNo);
		model.addAttribute("lastpage", totalLinks);
		 model.addAttribute("list",list);
		 model.addAttribute("totalLinks", totalLinks);
		model.addAttribute("totalSize", size);
		return "viewStudents";
	}
	@GetMapping(path="/editstudentprofile/{studentId}/{isEdit}")
	public String EditStudent(@PathVariable("studentId") Long studentId,@PathVariable("isEdit") int isEdit,ModelMap model,StudentdtBean studentdtBean) {
		try {
		Optional<Student> s = studentRepo.findById(studentId);
		Student student = s.get();
		model.addAttribute("student", student);
		studentdtBean.setStudentId(Integer.parseInt(student.getStudentId().toString()));
		studentdtBean.setEmailId(student.getEmailId());
		studentdtBean.setStudentName(student.getStudentName());
		studentdtBean.setGender(student.getGender());
		//studentdtBean.setCity(student.getCity());
		studentdtBean.setAddress(student.getAddress());
		String[] hobbyArr = new String[student.getHobbies().size()];
		String[] skillArr = new String[student.getSkills().size()];
		int hobbcount = 0;
		int skillcount = 0;
		for(Hobby hobbyset : student.getHobbies()) {
			hobbyArr[hobbcount] = hobbyset.getHobbyName();
			hobbcount++;
		}
		for(Skill skillset : student.getSkills()) {
			skillArr[skillcount] = skillset.getSkillName();
			skillcount++;
		}
		studentdtBean.setDate(student.getDate().toString());
		studentdtBean.setHobbies(hobbyArr);
		studentdtBean.setSkills(skillArr);
		studentdtBean.setCountry(countryRepo.getCountryIdByName(student.getCountry()));
		studentdtBean.setState(stateRepo.getStateIdByName(student.getState()));
		studentdtBean.setCity(cityRepo.getCityIdByName(student.getCity()));
		String[] hobbies = {"Cricket","Football","Volley ball","Basket ball"};
		
		  String[] skills = new String[4]; skills[0] = "Java"; skills[1] = "Android";
		  skills[2] = "PHP"; skills[3] = "Python";
		 
		model.addAttribute("hobbies", hobbies);
		 model.addAttribute("skills", skills);
		 List<Country> countries = countryRepo.findAll();
		 countries.add(0, new Country(new Long(0),"Please Select"));
		 Long countryId = Long.parseLong(new Integer(countryRepo.getCountryIdByName(student.getCountry())).toString());
		 List<State> states = stateRepo.getStatesByCountryId(countryId);
		 states.add(0,new State(new Long(0),"Please Select"));
		 List<City> cities = cityRepo.getCitiesByStateId(new Long(stateRepo.getStateIdByName(student.getState())));
		 cities.add(0,new City(new Long(0),"Please Select"));
		 model.addAttribute("countries", countries);
		 model.addAttribute("states", states);
		 model.addAttribute("cities", cities);
		if(isEdit == 1) {
		model.addAttribute("isEdit", true);
		}
		return "first";
		}
		catch(Exception e){
			return "errorPage";
		}
	}
	@PostMapping(path="/updatestudent")
	public String updateStudent(@ModelAttribute StudentdtBean studentdtBean,RedirectAttributes redirectAttributes) {
		try {
		System.out.println("Inside update method");
		Set<Hobby> hobbies = new HashSet<Hobby>();
		Set<Skill> skills = new HashSet<Skill>();
		
		Integer i = new Integer(studentdtBean.getStudentId());
		Optional<Student> st = studentRepo.findById(Long.parseLong(i.toString()));
		Student s = st.get();
		//s.setStudentId(Long.parseLong(i.toString()));
		s.setEmailId(studentdtBean.getEmailId());
		s.setStudentName(studentdtBean.getStudentName());
		s.setGender(studentdtBean.getGender());
		s.setAddress(studentdtBean.getAddress());
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateAsString = studentdtBean.getDate();
		Date date = sourceFormat.parse(dateAsString);  
		s.setDate(date);
		s.setCountry(countryRepo.getCountryNameById(Long.parseLong(new Integer(studentdtBean.getCountry()).toString())));
		Long stateId = new Long(studentdtBean.getState());
		s.setState(stateRepo.getStateNameById(stateId));
		Long cityId = new Long(studentdtBean.getCity());
		s.setCity(cityRepo.getCityNameById(cityId));
		for(String str:studentdtBean.getHobbies()) {
			Hobby h = new Hobby();
			h.setHobbyName(str);
			h.setStudent(s);
			hobbies.add(h);
			}
		for(String str:studentdtBean.getSkills()) {
			Skill sk = new Skill();
			sk.setSkillName(str);
			sk.setStudent(s);
			skills.add(sk);
			}
		
		//studentRepo.updateStudentProfile(s.getStudentName(),s.getGender(),s.getAddress(),s.getCity(),s.getStudentId());
		hobbyRepo.deleteInBatch(s.getHobbies());
		skillRepo.deleteInBatch(s.getSkills());
		s.setHobbies(hobbies);
		s.setSkills(skills);
		studentRepo.save(s);
		redirectAttributes.addFlashAttribute("saveSuccess", "Student updated successfully");
		return "redirect:/viewStudentsmapping/1";
		}
		catch(Exception e) {
			return "errorPage";
		}
	}
	@GetMapping(path="/deletestudentprofile/{studentId}")
	public String deleteStudent(@PathVariable("studentId") Long studentId) {
		jdbcTemplate.update(
			      "DELETE FROM users_roles WHERE studentId = ? ", studentId);
		studentRepo.deleteById(studentId);
		return "redirect:/viewStudentsmapping/1";
	}
	@GetMapping(path="/uploadimage/{studentId}")
	public String uploadImage(@PathVariable("studentId") Long studentId, ModelMap model) {
		List<String> listImages = new ArrayList<String>();
		List<Integer> listImageIds = new ArrayList<Integer>();
		model.addAttribute("studentId", studentId);
		Optional<Student> s = studentRepo.findById(studentId);
		Student student = s.get();
		if(!student.getStudentImage().isEmpty() && student.getStudentImage() != null) {
			for(StudentImage si:student.getStudentImage()) {
				try {
					byte[] bytes = si.getImage();
					byte[] encodeBase = Base64.encodeBase64(bytes);
					String base64Encoded = new String(encodeBase, "UTF-8");
					System.out.println("___________IMAGE__________");
					System.out.println(base64Encoded);
					System.out.println("___________IMAGE__________");
					listImages.add(base64Encoded);
					listImageIds.add(Integer.parseInt(si.getImageId().toString()));
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("studentId", student.getStudentId());
		model.addAttribute("encodedImage", listImages);
		model.addAttribute("imageId", listImageIds);
		return "uploadImageMapping";
	}
	@PostMapping(path="/saveuploadimage")
	public String saveUploadImage(@RequestParam("studentImage") MultipartFile file,HttpServletRequest request,RedirectAttributes redirectAttributes) throws IOException {
		String studentId="";
		Long l = null;
		File pathFile=null;
		File actualFile = null;
		FileOutputStream fos = null;
		if(!file.isEmpty()) {
			try {
				studentId = request.getParameter("hdstudentId");
				l = Long.parseLong(studentId.toString());
				Optional<Student> s = studentRepo.findById(l);
				Student student = s.get();
				String description = request.getParameter("studentPhoto");
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				byte[] image = file.getBytes();
				boolean isWindows = commonMethod.isWindows();
				if(isWindows) {
				String path = rootUploadPath+"\\"+student.getStudentName()+"_"+student.getStudentId().toString();
				pathFile = new File(path);
				actualFile = new File(path+"\\"+fileName);
				if(!pathFile.exists()) {
					pathFile.mkdirs();
				}
				if(!actualFile.exists()) {
					actualFile.createNewFile();
					fos = new FileOutputStream(actualFile);
					fos.write(image);
				}
				}
				else {
					String path = "/home/ubuntu/serverData"+"/"+student.getStudentName()+"_"+student.getStudentId().toString();
					pathFile = new File(path);
					actualFile = new File(path+"/"+fileName);
					if(!pathFile.exists()) {
						pathFile.mkdirs();
					}
					if(!actualFile.exists()) {
						actualFile.createNewFile();
						fos = new FileOutputStream(actualFile);
						fos.write(image);
					}
				}
				StudentImage si = new StudentImage();
				si.setImageDescription(description);
				si.setStudent(student);
				si.setImage(image);
				si.setPath(actualFile.getAbsolutePath());
				studentImageRepo.save(si);
				redirectAttributes.addFlashAttribute("uploadSuccess", "Image Uploaded successfully");
		}
			finally {
				fos.flush();
				if(fos !=  null) {
				fos.close();
				}
			}
		}
		
		return "redirect:/uploadimage/"+l.toString();
	}
	
	@GetMapping(path="/downloadimage/{studentId}/{imageId}/{opType}")
	public void downloadImage(@PathVariable("studentId") Long studentId,@PathVariable("imageId") Long imageId,@PathVariable("opType") String opType,HttpServletResponse response) {
		System.out.println("downloading Image");
		try {
		Optional<StudentImage> s = studentImageRepo.findById(imageId);
		StudentImage si = s.get();
		File file = new File(si.getPath());
		String mimeType= URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
		response.setContentType(mimeType);
		if(opType.equalsIgnoreCase("download")) {
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		}
		else {
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
		}
		response.setContentLength((int)file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	@GetMapping(path="/getStateComboByCountryId/{countryId}")
	@ResponseBody
	public String getStatesCombo(@PathVariable("countryId") Long countryId) {
		String stateVal = "<option value=0>Please Select</option>";
		List<State> states = stateRepo.getStatesByCountryId(countryId);
		for(State state:states) {
			stateVal=stateVal+"<option value="+state.getStateId()+">"+state.getStateName()+"</option>";
		}
		return stateVal;
	}
	@GetMapping(path="/getCityComboByStateId/{stateId}")
	@ResponseBody
	public String getCityCombo(@PathVariable("stateId") Long stateId) {
		String cityVal = "<option value=0>Please Select</option>";
		List<City> cities = cityRepo.getCityByStateId(stateId);
		for(City city:cities) {
			cityVal=cityVal+"<option value="+city.getCityId()+">"+city.getCityName()+"</option>";
		}
		return cityVal;
	}
	@GetMapping(path="/login")
	public String getLoginPage(ModelMap model) {
		//model.addAttribute("command", "Hello Ankit");
		return "loginPage";
	}
	@PostMapping(path="/loginSave")
	public String getLoginSuccess(ModelMap model) {
		return "redirect:/home";
	}
	@GetMapping(path="/error")
	public String getErrorHandle(ModelMap model,Exception e) {
		e.printStackTrace();
		return "errorPage";
	}
	@GetMapping(path="/viewstudentprofile/{studentId}")
	public String getViewProfile(ModelMap model,@PathVariable("studentId") Long studentId) {
		Optional<Student> s = studentRepo.findById(studentId);
		Student student = s.get();
		model.addAttribute("student", student);
		return "ViewProfile";
	}
	@GetMapping(path="/searchStudentmapping")
	public String searchStudent(ModelMap model) {
		
		return "SearchStudent";
	}
	@GetMapping(path="/getStudentBySearchCriteria/{searchVal}")
	@ResponseBody
	public String getCityCombo(@PathVariable("searchVal") String searchVal) {
		String retVal = "";
		List<Student> students = studentRepo.getStudentByMailIdOrName(searchVal);
		if(students != null && !students.isEmpty()) {
		for(Student student:students) {
			retVal=retVal+"<a href=\""+"/viewstudentprofile/"+student.getStudentId().toString()+"\">"+student.getStudentName()+"</a><br/>"+"Mail : "+student.getEmailId()+"<br/>";
		}
		}
		else {
			retVal="No Data Available";
		}
		return retVal;
	}
	@GetMapping(path="/viewStudentByPage/{linkNo}")
	public String ViewStudentsByPagination(ModelMap model,@PathVariable("linkNo") int linkNo) {
		Long totalNoLinks = studentRepo.count();
		totalNoLinks = totalNoLinks % 10 == 0 ? totalNoLinks :  totalNoLinks +1;
		int offset = linkNo == 1 ? 0 : linkNo*10-10;
		Pageable pageable = PageRequest.of(linkNo-1,10);
		Page<Student> pageDetails =  studentRepo.findAll(pageable);
		List<Student> list = pageDetails.getContent();
		long size = pageDetails.getTotalElements();
		model.addAttribute("totalSize", size);
		model.addAttribute("list",list);
		return "viewStudents";
	}
	@RequestMapping(value = "/user/getEmployeesList", produces = "application/json")
    @ResponseBody
    public Map<String,Object> getEmployeesList(Principal principal) {
		System.out.println(principal);
		String emailId = principal.getName();
		Student student = studentRepo.getStudentByMailId(emailId);
        Map<String,Object> empDetails = new HashMap<String,Object>();
        empDetails.put("name", student.getStudentName());
        empDetails.put("emailId", student.getEmailId());
        empDetails.put("hobbies", student.getHobbies());
        System.out.println(student.getDate().toString().substring(0, 10));
        LocalDate dob = LocalDate.parse(student.getDate().toString().substring(0, 10));  
        empDetails.put("age", commonMethod.calculateAge(dob));
        return empDetails;

    }

}
