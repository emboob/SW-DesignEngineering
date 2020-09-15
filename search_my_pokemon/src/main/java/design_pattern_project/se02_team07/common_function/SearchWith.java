package design_pattern_project.se02_team07.common_function;

import java.util.List;

/**
 * 상태 패턴을 이용하는 검색의 인터페이스
 *
 * @author  20163371 전찬혁
 * @version 1.0.0
 * @since   2019.05.28 인터페이스 최초 제작
 */
public interface SearchWith {

    List<Pokemon> search(final SearchPokemon searchPokemon, 
            List<Pokemon> searchList,String str);
    
}
