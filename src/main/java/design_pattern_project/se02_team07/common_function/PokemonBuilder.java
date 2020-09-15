package design_pattern_project.se02_team07.common_function;

/**
 * 포켓몬 정보를 생성하는 빌더 패턴의 Implement Builder 클래스
 *
 * @author  20153265 김동현
 * @version 1.0.0
 * @since   2019.05.24 스트레티지 패턴 불합격으로 인하여 교수님께 조언을 받아 빌더 패턴으로 클래스 생성
 *           2019.05.25 포켓몬 이미지 경로 설정 메소드 추가
 *           2019.05.26 setPokeNum 메소드에 포켓몬 이미지 경로 통합
 *           2019.06.02 자바 빌더 형식으로 빌더 패턴 변경
 */
public class PokemonBuilder {
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
     * @author 김동현
     */
    private String pokeNum;
    private String pokeName;
    private String mainType;
    private String subType;
    private String baseStats;
    private String evolvable;
    private String finalLevel;
    private String currentLevel;
    
    /**
     * 포켓몬 빌더를 이용하여 포켓몬 객체의 포켓몬 번호와 포켓몬 이미지 경로를
     * 설정하는 메소드
     * 
     * @param pokeNum this.pokeNum에 들어갈 정보
     * @return 빌더에 지정된 포켓몬 번호와 포켓몬 이미지 경로
     */
    public PokemonBuilder setPokeNum(String pokeNum) {
        this.pokeNum = pokeNum;
        
        return this;
    }
    
    /**
     * 포켓몬 빌더를 이용하여 포켓몬 객체의 포켓몬 이름을 지정하는 메소드
     * 
     * @param pokeName this.pokeName에 들어갈 정보
     * @return 빌더에 지정된 포켓몬 이름
     */
    public PokemonBuilder setPokeName(String pokeName) {
        this.pokeName = pokeName;
        
        return this;
    }
    
    /**
     * 포켓몬 빌더를 이용하여 포켓몬 객체의 포켓몬 메인 타입과 포켓몬 메인 타입
     * 이미지 경로를 지정하는 메소드
     * 
     * @param mainType this.mainType에 들어갈 정보
     * @return 빌더에 지정된 메인 타입과 메인 타입 이미지 경로
     */
    public PokemonBuilder setMainType(String mainType) {
        this.mainType = mainType;
        
        return this;
    }
    
    /**
     * 포켓몬 빌더를 이용하여 포켓몬 객체의 포켓몬 서브 타입과 포켓몬 서브 타입
     * 이미지 경로를 지정하는 메소드
     * 
     * @param subType this.subType에 들어갈 정보
     * @return 빌더에 지정된 서브 타입과 서브 타입 이미지 경로
     */
    public PokemonBuilder setSubType(String subType) {
        this.subType = subType;
        
        return this;
    }
    /**
     * 포켓몬 빌더를 이용하여 포켓몬 객체의 능력치를 설정하는 메소드
     * 
     * @param baseStats this.baseStats에 들어갈 정보
     * @return 빌더에 지정된 포켓몬의 능력치
     */    
    public PokemonBuilder setBaseStats(String baseStats) {
        this.baseStats = baseStats;
        
        return this;
    }
    /**
     * 포켓몬 빌더를 이용하여 포켓몬 객체의 진화 가능 여부를 설정하는 메소드
     * 
     * @param evolvable this.evolvable에 들어갈 정보
     * @return 빌더에 지정된 포켓몬의 진화 가능 여부
     */
    public PokemonBuilder setEvolvable(String evolvable) {
        this.evolvable = evolvable;
        
        return this;
    }
    
    /**
     * 포켓몬 빌더를 이용하여 포켓몬 객체의 최종 진화 단계를 설정하는 메소드
     * 
     * @param finalLevel this.finalLevel에 들어갈 정보
     * @return 빌더에 지정된 포켓몬의 최종 진화 단계
     */
    public PokemonBuilder setFinalLevel(String finalLevel) {
        this.finalLevel = finalLevel;
        
        return this;
    }
    
    /**
     * 포켓몬 빌더를 이용하여 포켓몬 객체의 현재 진화 단계를 설정하는 메소드
     * 
     * @param currentLevel this.currentLevel에 들어갈 정보
     * @return 빌더에 지정된 포켓몬의 현재 진화 단계
     */
    public PokemonBuilder setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
        
        return this;
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
    
    /**
     * 포켓몬 빌더를 이용하여 설정한 메소드들로 새로운 포켓몬을 만드는 메소드
     * 
     * @return 새로운 포켓몬 객체
     */
    public Pokemon createPokemon(){
        return new Pokemon(this);
    }
    
    /**
     * 포켓몬 빌더 객체를 하나만 사용할때 설정된 내용을 초기화시켜주는 메소드.
     */
    public void resetBuilder() {
        this.pokeNum = null;
        this.pokeName = null;
        this.mainType = null;
        this.subType = null;
        this.baseStats = null;
        this.evolvable = null;
        this.finalLevel = null;
        this.currentLevel = null;
    }
    
}
