package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest // DB 관련 객체들이 IoC에 뜬다.
public class BoardRepositoryTest {
    @Autowired // Test에서 DI하는 코드
    private BoardRepository boardRepository;

    @Test
    public void delete_test () {
        //Given
        int id = 1;
        //when
        boardRepository.delete(id);
        //then
        List<Board> boardList = boardRepository.selectAll();
        System.out.println(boardList.size());
    }

    @Test
    public void updateById_test (){
        //GIVEN
        int id = 1;
        String title = "안녕1";
        String content = "안녕1";
        String author = "광개토";
        //WHEN
        boardRepository.update(id, title, content, author);

        //THEN
        Board board = boardRepository.selectOne(id);
        Assertions.assertThat(board.getTitle()).isEqualTo("안녕1");
        Assertions.assertThat(board.getContent()).isEqualTo("안녕1");
        Assertions.assertThat(board.getAuthor()).isEqualTo("광개토");
    }

    @Test
    public void selectAll_test () {
        //when
        List<Board> boardList = boardRepository.selectAll();
        //then


        Assertions.assertThat(boardList.get(0).getTitle()).isEqualTo("제목1");
        Assertions.assertThat(boardList.get(0).getContent()).isEqualTo("내용1");
        Assertions.assertThat(boardList.get(0).getAuthor()).isEqualTo("홍길동");
        System.out.println(boardList.size());
    }


    @Test
    public void selectOne_test() {
        // given
        int id = 1;
        // when
        Board board = boardRepository.selectOne(id);
        // then  (상태 검사)
        // System.out.println(board);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
        Assertions.assertThat(board.getAuthor()).isEqualTo("홍길동");
    }

    @Test
    public void insert_test() { // 테스트 메서드는 파라미터가 없다. 리턴도 없다.
        // given
        String title = "제목1";
        String content = "내용10";
        String author = "이순신";

        // when
        boardRepository.insert(title, content, author);

        // then -> 눈으로 확인 (쿼리)
    } // Rollback (자동)
}