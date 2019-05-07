package tk.mybatis.simple.model;

/*
 * MyBatis是一个结果映射框架，这里创建的实体类实际上是一个数据值对象，
 * 在实际应用中，一个表一般会对应一个实体，用于INSERT, UPDATE, DELETE和简单的SELECT操作
 *
 */
public class Country {
    private Long id;
    private String countryname;
    private String countrycode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
}
