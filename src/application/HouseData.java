package application;

public class HouseData {

	private Integer houseId;
	private String name;
	private String region;
//JavaBeans 규약에 따르면 속성 이름이 propertyName일 때, 해당 속성의 값을 읽어오는 getter 메서드는 getPropertyName()
	HouseData(int s1, String s2, String s3) {
		houseId = s1;
		name = s2;
		region = s3;
	}
    public Integer getHouseId() {
        return houseId;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }
}
