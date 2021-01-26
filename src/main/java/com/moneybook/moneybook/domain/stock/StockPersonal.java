package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import com.moneybook.moneybook.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StockPersonal extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_information_id")
    private StockInformation stockInformation;

    private Long targetQuantity;
    private Long currentQuantity;

    @Builder
    public StockPersonal(Member member, StockInformation stockInformation, Long targetQuantity, Long currentQuantity) {
        this.member = member;
        this.stockInformation = stockInformation;
        this.targetQuantity = targetQuantity;
        this.currentQuantity = currentQuantity;
    }

    public void changeTargetQuantity(Long newTargetQuantity) {
        this.targetQuantity = newTargetQuantity;
    }

    public void buyStock(Long buyQuantity) {
        this.currentQuantity += buyQuantity;
    }

    public void sellStock(Long sellQuantity) {
        if(this.currentQuantity - sellQuantity < 0){
            throw new IllegalArgumentException("sell too much");
        }
        this.currentQuantity -= sellQuantity;
    }
}
