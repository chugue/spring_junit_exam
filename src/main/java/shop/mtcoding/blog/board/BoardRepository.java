package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void delete(int id){
        String q = """
                delete from board_tb 
                where id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        System.out.println(1);
    }


    @Transactional
    public void update(int id, String title, String content, String author){
        String q = """
                update board_tb 
                set title= ?, content=?, author=?
                where id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, author);
        query.setParameter(4, id);
        query.executeUpdate();
    }

    public List<Board> selectAll() {
        String q = """
                select * from board_tb;
                """;
        Query query = em.createNativeQuery(q, Board.class);
        List<Board> boardList = query.getResultList(); // 더미 값이 없으면 null이 아니라 빈 배열을 준다. trycatch안해도된다.
        return boardList;
    }


    public Board selectOne(int id){
        String q = """
                select * from board_tb where id = ?
                """;
        Query query = em.createNativeQuery(q, Board.class);
        query.setParameter(1, id);

        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void insert(String title, String content, String author){
        Query query = em.createNativeQuery("insert into board_tb(title, content, author) values(?, ?, ?)");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, author);

        query.executeUpdate();
    }
}