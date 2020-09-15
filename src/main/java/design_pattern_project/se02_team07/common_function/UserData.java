package design_pattern_project.se02_team07.common_function;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 로그인시 유저의 정보를 저장하기 위한 클래스
 *
 * @author  20153270 이승윤
 * @version 1.0.0
 * @since   2019.05.21 클래스 최초생성 및 PokemonList 추가
 *           2019.05.27 유저 데이터 업데이트 기능 추가
 *           2019.05.29 로그인 여부 확인 메소드 추가
 *           2019.05.31 소나큐브 오류 수정
 *           2019.06.01 updateUserData() 메소드 오류 수정
 */
public class UserData {
    private static String userID;
    private static String userPass;
    private static String userName;
    private static String dexPath;
    private static int index = 0;
    private static final String PATH = "src/main/resources/texts/login/member.txt";
    private static List<String> pokemonList;
    private static boolean userState = false;
    
    private UserData(){
    }
    
    /**
     * 로그인 데이터 읽어오는 메소드
     * 
     * @param txtID 유저가 GUI에 입력한 ID
     * @param txtPass 유저가 GUI에 입력한 비밀번호
     */
    public static void loginData(String txtID, String txtPass){
        Path path = Paths.get(PATH);
        try(BufferedReader reader = Files.newBufferedReader(path)){
            String line = "";
            while((line = reader.readLine()) != null){
                String[] userarr = line.split(" ");
                String id = userarr[0];
                String pass = userarr[1];
                String name = userarr[2];
                if(txtID.equals(id)&&txtPass.equals(pass)){
                    ArrayList<String> tempPokemonList = new ArrayList<>();
                    for(int i=3;i<userarr.length;i++){
                        tempPokemonList.add(userarr[i]);
                    }
                    UserData.userID = id;
                    UserData.userPass = pass;
                    UserData.userName = name;
                    UserData.pokemonList = tempPokemonList;
                    userDexLoad();
                    userState = true;
                    break;
                }
                index++;
            }
        }catch (FileNotFoundException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException e){
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * ID 중복 비교 메소드
     * 
     * @param getId 유저가 회원가입 할때 입력한 아이디
     * @return true(중복) or false(중복x)
     */
    public static boolean compareId(String getId){
        Path path = Paths.get(PATH);
        try(BufferedReader reader = Files.newBufferedReader(path)){
            String line = "";
            while((line = reader.readLine()) != null){
                String[] userarr = line.split(" ");
                String id = userarr[0];
                if(getId.equals(id)){
                    return true;
                }
            }
        }catch (FileNotFoundException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException e){
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public static void setUserState(boolean userState) {
        UserData.userState = userState;
    }

    
    public static boolean getUserState() {
        return userState;
    }
    
    public static String getID() {
        return userID;
    }

    public static String getPass() {
        return userPass;
    }

    public static String getName() {
        return userName;
    }

    public static int getIndex() {
        return index;
    }

    public static List<String> getPokemon() {
        return pokemonList;
    }

    public static String getDexPath() {
        return dexPath;
    }

    public static void setPokemon(List<String> pokemon) {
        UserData.pokemonList = pokemon;
    }

    /**
     * 유저의 데이터가 프로그램 내에서 변경되면 업데이트 해주는 메소드
     *
     * @author 전찬혁
     */
    public static void updateUserData() {
        String temp = userID + " " + userPass + " " + userName + " ";
        StringBuilder line = new StringBuilder(temp);
        for (String s : pokemonList) {
            line.append(s);
            line.append(" ");
        }
        StringBuilder tempLine = new StringBuilder("");
        Path path = Paths.get(PATH);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String thisLine;
            for (int i = 0; i < index; i++) {
                thisLine = reader.readLine();
                tempLine.append(thisLine).append("\r\n");
            }
            thisLine = reader.readLine();
            tempLine.append(line.toString()).append("\r\n");
            while ((thisLine = reader.readLine()) != null) {
                tempLine.append(thisLine).append("\r\n");
            }
            writeUserData(tempLine);
        } catch (FileNotFoundException e) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 회원가입시 유저 데이터를 쓰는 메소드
     * 
     * @param tempLine 스트링빌더로 내용을 읽어와 내용을 기록함. 
     */
    public static void writeUserData(StringBuilder tempLine){
        try (FileWriter fw = new FileWriter(PATH)) {
                fw.write(tempLine.toString());
        }catch (FileNotFoundException e) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 개인 유저와 개인 도감을 연결해주는 메소드.
     * 
     * <pre>
     *  File.exists() = 파일이 존재하는지 체크한 후 boolean형 리턴
     * </pre>
     * @author 전찬혁
     */
    public static void userDexLoad() {
        String userDexPath = "src/main/resources/texts/pokemon/" + userName + "-PersonalDex.txt";
        File personalDex = new File(userDexPath);
        boolean isExists = personalDex.exists();
        if (isExists) {
            dexPath = userDexPath;
        } else {
            dexPath = userDexPath;
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dexPath,true),StandardCharsets.UTF_8))) {
                writer.write(userName+"의 개인 도감");
                writer.flush();
            } catch (FileNotFoundException e) {
                Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, e);
            } catch (IOException ex) {
                Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
