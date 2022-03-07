package com.finalproject.springbootfoodapp.entity.reqres;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetCourierToOrderRequest {

    private Long courierId;
    private Long orderId;
}
