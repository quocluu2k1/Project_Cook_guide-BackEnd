package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Long> {
    @Query(value="SELECT sum(r_claps) from reaction where food_id = ?1", nativeQuery = true)
    public Long countReactClap(Long id);

    @Query(value="SELECT sum(r_hearts) from reaction where food_id = ?1", nativeQuery = true)
    public Long countReactHeart(Long id);

    @Query(value="SELECT sum(r_savoring) from reaction where food_id = ?1", nativeQuery = true)
    public Long countReactSavoring(Long id);

    @Query(value="SELECT COUNT(*) FROM reaction WHERE r_claps=1 and id=?1 and food_id=?2", nativeQuery = true)
    public Long getStatusReactClap(Long userId, Long foodId);

    @Query(value="SELECT COUNT(*) FROM reaction WHERE r_hearts=1 and id=?1 and food_id=?2", nativeQuery = true)
    public Long getStatusReactHeart(Long userId, Long foodId);

    @Query(value="SELECT COUNT(*) FROM reaction WHERE r_savoring=1 and id=?1 and food_id=?2", nativeQuery = true)
    public Long getStatusReactSavoring(Long userId, Long foodId);

    @Query(value="SELECT * FROM reaction WHERE id=?1 and food_id=?2", nativeQuery = true)
    public Reaction findByUserIdAndFoodId(Long userId, Long foodId);
}
