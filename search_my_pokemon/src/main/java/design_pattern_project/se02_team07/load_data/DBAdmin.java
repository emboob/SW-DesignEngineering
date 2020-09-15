package design_pattern_project.se02_team07.load_data;

import design_pattern_project.se02_team07.common_function.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator 패턴의 Client에 해당하는 클래스
 *
 * @author  20173116 신휘정
 * @version 1.0.0
 * @since   2019.05.22 클래스 첫 작성.
 *           2019.05.23 이터레이터 패턴에 맞춰서 클래스 재정렬 및 수정 return값 오류로 인해 계속 고침.
 *           2019.05.24 교수님께 return값 여쭤보고 문제 해결.
 *           2019.05.27 클래스에 연결하기위해 수정
 */
public class DBAdmin {
    private final DBList dataList;
    
    private String num;
    private String name;
    private String mainType;
    private String subType;
    private String baseStats;
    private String evolvable;
    private String finalLevel;
    private String currentLevel;
    
    private final List<Pokemon> pokemonList = new ArrayList<>();
    
    /**
     * DBAdmin의 생성자
     * 
     * @param dataList 저장된 데이터 값
     */
    public DBAdmin(DBList dataList){
        this.dataList = dataList;
    }
    
    /**
     * DB의 값을 읽어와 값들을 각자 만들어줌
     * 
     * @param str 
     */
    private void getPokemonList(String str){
        num = str.split("/",-1)[0];
        name = str.split("/",-1)[1];
        mainType = str.split("/",-1)[2];
        subType = str.split("/",-1)[3];
        baseStats = str.split("/",-1)[4];
        evolvable = str.split("/",-1)[5];
        finalLevel = str.split("/",-1)[6];
        currentLevel = str.split("/",-1)[7];
    }
    
    /**
     * makeDB를 오버라이드하여 이터레이터를 이용하여 dataList를 받아온다.
     * 
     * @return 포켓몬리스트에 상태를 추가함
     */
    public List<Pokemon> makeDB(){
        Iterator<String> dataListIterator = dataList.createIterator();
        makeDB(dataListIterator);
        return pokemonList;
    }
    
    /**
     * 디비를 구성하는 메소드로 이터레이터를 받아와 포켓몬 리스트를 생성한다.
     * 
     * @param iterator 
     */
    private void makeDB(Iterator<String> iterator){
        PokemonBuilder pokemonBuilder = new PokemonBuilder();
        while(iterator.hasNext()){
            pokemonBuilder.resetBuilder();
            getPokemonList((String)iterator.next());
            pokemonBuilder.setPokeNum(num);
            pokemonBuilder.setPokeName(name);
            pokemonBuilder.setMainType(mainType);
            if(!subType.equals("")) pokemonBuilder.setSubType(subType);
            pokemonBuilder.setBaseStats(baseStats);
            pokemonBuilder.setEvolvable(evolvable);
            pokemonBuilder.setFinalLevel(finalLevel);
            pokemonBuilder.setCurrentLevel(currentLevel);
            pokemonList.add(pokemonBuilder.createPokemon());
        }
    }
    
}
