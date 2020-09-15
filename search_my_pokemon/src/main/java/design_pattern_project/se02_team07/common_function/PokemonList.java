package design_pattern_project.se02_team07.common_function;

import design_pattern_project.se02_team07.load_data.DBAdmin;
import design_pattern_project.se02_team07.load_data.FormalDB;
import design_pattern_project.se02_team07.load_data.PersonalDB;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * 포켓몬 리스트를 만들고 관리하는 클래스
 *
 * @author  20163371 전찬혁
 * @version 1.0.0
 * @since   2019.05.25 클래스 최초 제작
 *           2019.05.28 검색 기능 따로 클래스로 제작
 *           2019.05.31 소나큐브 오류 수정
 */
public final class PokemonList {
    static List<Pokemon> pokeList;
    

    private PokemonList(){
    }
    /**
     * 포켓몬 리스트 설정
     * 리스트가 변경되면 한번 실행 해주면 된다.
     * 
     * @author 전찬혁
     */
    public static void setPokemonList() {
        pokeList = new ArrayList<>();
        pokeList = new DBAdmin(new FormalDB()).makeDB();
        if(UserData.getName() != null){
            pokeList.addAll(new DBAdmin(new PersonalDB(UserData.getDexPath())).makeDB());
        }
        pokeList = sortList(pokeList);
    }

    public static List<Pokemon> getPokeList() {
        return pokeList;
    }
    
    /**
     * 리스트에서 원하는 범위의 도감 번호로 포켓몬을 분류하여 리턴하는 메서드
     * 
     * @param searchList 검색을 실행할 리스트
     * @param minNum 최소 포켓몬 번호
     * @param maxNum 최대 포켓몬 번호
     * @return temp 포켓몬 리스트
     */
    public static List<Pokemon> makeListWithNum(List<Pokemon> searchList,int minNum, int maxNum) {
        List<Pokemon> temp = new ArrayList<>();
        for (Pokemon p : searchList) {
            if (Integer.parseInt(p.getPokeNum()) >= minNum && Integer.parseInt(p.getPokeNum()) <= maxNum) {
                temp.add(p);
            }
        }
        return temp;
    }

    /**
     * 포켓몬을 추가하는 메서드
     * 
     * @param line 입력된 각 라인
     */
    public static void addPokemon(String line){
        List<Integer>pokeNumList = new ArrayList<>();
        for(Pokemon p : pokeList){
            pokeNumList.add(Integer.parseInt(p.getPokeNum()));
        }
        if(pokeNumList.contains(Integer.parseInt(line.split("/")[0]))){
            JOptionPane.showMessageDialog(null,"이미 추가된 포켓몬 입니다.");
        }else{
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/texts/pokemon/"+UserData.getName()+"-PersonalDex.txt",true),StandardCharsets.UTF_8))) {
                /**
                 * writer.newLine() = 줄바꿈
                 * <pre>
                 *  writer.newLine()
                 * </pre>
                 * 
                 * @author 전찬혁
                 */
                writer.write(line);
                writer.flush();
            } catch (FileNotFoundException e) {
                Logger.getLogger(PokemonList.class.getName()).log(Level.SEVERE, null, e);
            } catch (IOException ex) {
                Logger.getLogger(PokemonList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setPokemonList();
    }
    
    /**
     * 포켓몬 리스트를 정렬하여 반환하는 메소드
     * @param pokemonList 정렬할 리스트
     * @return temp 번호기준으로 정렬된 포켓몬을 리스트로 반환
     * 
     */
    public static List<Pokemon> sortList(List<Pokemon> pokemonList){
        List<Pokemon>temp = new ArrayList<>();
        List<Integer>pokeNumList = new ArrayList<>();
        for(Pokemon p : pokemonList){
            pokeNumList.add(Integer.parseInt(p.getPokeNum()));
        }
        Collections.sort(pokeNumList);
        for (Integer i : pokeNumList) {
            for (Pokemon pokemon : pokemonList) {
                if (Integer.parseInt((pokemon.getPokeNum())) == i) {
                    temp.add(pokemon);
                }
            }
        }
        
        return temp;
    }
    
}
