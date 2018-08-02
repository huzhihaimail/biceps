package cn.com.njdhy.muscle.biceps.model;

/**
 * 面试者登记表
 *
 * @author jason.hu
 * @date 2018-08-01
 */
public class HrInterview extends BaseModel {

    /**
     * 面试人名称
     */
    private String name;

    /**
     * 年龄
     */
    private String age;
    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 学历
     */
    private String education;

    /**
     * 毕业院校
     */
    private String school;

    /**
     * 实际工作年限
     */
    private String workYear;

    /**
     * 工作城市
     */
    private String workCity;

    /**
     * 最快入职时间
     */
    private String workOnTime;

    /**
     * 期望薪资
     */
    private String expectedSalary;

    /**
     * 面试类型（电话面试、现场面试）
     */
    private String testType;

    /**
     * 是否有offer
     */
    private String offerFlag;

    /**
     * 是否推荐
     */
    private String recommendFlag;


    /**
     * 邮箱
     */
    private String email;

    /**
     * 岗位类型
     */
    private String techType;

    /**
     * 登记人
     */
    private String recordUser;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTechType() {
        return techType;
    }

    public void setTechType(String techType) {
        this.techType = techType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getWorkOnTime() {
        return workOnTime;
    }

    public void setWorkOnTime(String workOnTime) {
        this.workOnTime = workOnTime;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getOfferFlag() {
        return offerFlag;
    }

    public void setOfferFlag(String offerFlag) {
        this.offerFlag = offerFlag;
    }

    public String getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(String recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public String getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }
}
