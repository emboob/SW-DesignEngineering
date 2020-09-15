package design_pattern_project.se02_team07.common_function;

import java.util.ArrayList;
import java.util.List;

/**
 * 상태 패턴 사용의 세번째 단계
 * 타입으로 포켓몬을 검색하는 클래스
 * 
 * @author  20163371 전찬혁
 * @version 1.0.0
 * @since   2019.05.28 클래스 최초 제작
 */
public class SearchWithType implements SearchWith {
    
    /**
     * 이름까지 검색이 끝난 상태면 오는 타입을 검색하며
     * 타입 검색까지 끝나면 모든 검색 절차가 끝이나고 검색결과가 출력된다.
     * 
     * @param searchPokemon 검색 객체 이 변수에 상태를 바꿔가며 쓴다.
     * @param searchList 검색을 실행할 리스트
     * @param str 검색어
     * @return temp 검색한 결과가 담긴 포켓몬 리스트
     */
    @Override
    public List<Pokemon> search(final SearchPokemon searchPokemon,List<Pokemon> searchList, String str) {
        if(str.equals("")){
            return searchList;
        }
        else{
            List<Pokemon> temp = new ArrayList<>();
            for (Pokemon p : searchList) {
                if (p.getSubType()!=null && p.getSubType().equals(str)||p.getMainType().equals(str)) {
                    temp.add(p);
                }
            }
            return temp;
        }
    }
    
}
