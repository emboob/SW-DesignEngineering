package design_pattern_project.se02_team07.common_function;

/**
 * 포켓몬 정보를 생성하는 빌더 패턴의 Product 클래스
 *
 * @author  20153265 김동현
 * @version 1.0.0
 * @since   2019.05.24 스트레티지 패턴 불합격으로 인하여 교수님께 조언을 받아 빌더 패턴으로 클래스 생성
 *           2019.05.25 포켓몬 이미지 경로 설정 메소드 추가 및 인스턴스 변수들의 자료형 수정
 *           2019.05.26 setPokeNum 메소드에 포켓몬 이미지 경로 통합
 *           2019.06.02 빌더 패턴 적용 방식 오류로 인해서 자바 빌더 패턴으로 변경하면서 수정함.
 */
public class Pokemon {
    /**
     * pokeNum = 포켓몬의 도감 번호
     * pokeImg = 포켓몬의 이미지 파일 경로
     * pokeName = 포켓몬의 이름
     * mainType = 포켓몬의 메인 타입
     * mainTypeImg = 포켓몬 메인 타입의 이미지 파일 경로
     * subType = 포켓몬의 서브 타입
     * subTypeImg = 포켓몬 서브 타입의 이미지 파일 경로
     * baseStats = 포켓몬의 종족값
     * evolvable = 포켓몬의 진화 가능 여부
     * finalLevel = 포켓몬의 최종 진화 단계 까지의 수
     * currentLevel = 포켓몬의 현재 진화 단계
     * 
     * @author 김동현, 전찬혁
     */
    private String pokeNum;
    private String pokeImg;
    private String pokeName;
    private String mainType;
    private String mainTypeImg;
    private String subType;
    private String subTypeImg;
    private String baseStats;
    private String evolvable;
    private String finalLevel;
    private String currentLevel;
    
    /**
     * 포켓몬 객체의 생성자 매개 변수를 전부 받을 경우 사용
     * 
     * @param builder 객체의 정보가 저장된 포켓몬 빌더 객체 
     */
    public Pokemon(PokemonBuilder builder) {
        this.pokeNum = builder.getPokeNum();
        this.pokeImg = "src/main/resources/images/pokeImg/" +
                this.pokeNum + ".png";
        this.pokeName = builder.getPokeName();
        this.mainType = builder.getMainType();
        this.mainTypeImg = "src/main/resources/images/typeImg/" +
                this.mainType + ".png";
        this.subType = builder.getSubType();
        this.subTypeImg = "src/main/resources/images/typeImg/" +
                this.subType + ".png";
        this.baseStats = builder.getBaseStats();
        this.evolvable = builder.getEvolvable();
        this.finalLevel = builder.getFinalLevel();
        this.currentLevel = builder.getCurrentLevel();
    }

    public String getPokeNum() {
        return pokeNum;
    }

    public String getPokeName() {
        return pokeName;
    }

    public String getMainType() {
        return mainType;
    }

    public String getSubType() {
        return subType;
    }
    
    public String getPokeImg() {
        return pokeImg;
    }

    public String getBaseStats() {
        return baseStats;
    }

    public String getEvolvable() {
        return evolvable;
    }

    public String getFinalLevel() {
        return finalLevel;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }
    
    public String getMainTypeImg() {
        return mainTypeImg;
    }
    
    public String getSubTypeImg() {
        return subTypeImg;
    }
    
}
