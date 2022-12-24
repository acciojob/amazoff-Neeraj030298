package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.*;
import java.util.List;
@Repository
public class OrderRepository {
    HashMap<String,String> orderpartnerpairmap = new HashMap<>();
    HashMap<String,Order> ordermap = new HashMap<>();
    HashMap<String,DeliveryPartner> partnermap = new HashMap<>();
public void addOrder(Order order)
{
    ordermap.put(order.getId(), order);
}
public void addpartner(String id)
{ DeliveryPartner partner = new DeliveryPartner(id);
    partnermap.put(partner.getId(), partner);
}
public void madeorderpartnerpair(String orderId, String partnerid){
    orderpartnerpairmap.put(orderId,partnerid);
}

public Order getOrderbyId(String id)
{ return ordermap.get(id);

}
public DeliveryPartner getPartnerbyId(String id)
    {return partnermap.get(id);
    }
public int numberOfOrder(String id)
{
    int count =0;
    for(String x : partnermap.keySet())
        if (x == id) count++;
    return count;
}
public List<String> orderAssignedToPartner(String id)
{ List<String> orderlist = new ArrayList<>();
    for(String x : orderpartnerpairmap.keySet()) {
        if (orderpartnerpairmap.get(x) == id) orderlist.add(x);
    }
        return orderlist;
    }
    public List<String> allOrder()
    {List<String> ord = new ArrayList<>();
        for(String x : ordermap.keySet()) ord.add(x);
        return ord;
    }
    public int nonAssignOrder()
    {int count =0;
        for (String x : ordermap.keySet()) {
            if (!orderpartnerpairmap.containsKey(x)) count++;
        }
            return count;
    }
        public int orderleft(String id, String time)
        {   String[] hourMin = time.split(":");
            int hour = Integer.parseInt(hourMin[0]);
            int mins = Integer.parseInt(hourMin[1]);
            int hoursInMins = hour * 60;
            int t1 =  hoursInMins + mins;
            int count =0;
            for(String x : orderpartnerpairmap.keySet())
            {
                if(orderpartnerpairmap.get(x) == id)
                {
                    Order order = ordermap.get(x);
                    if(order.getDeliveryTime()>t1) count++;
                }
            }
            return count;
        }
        public String lastDelivery(String id)
        {   int max =0;
            for(String x : orderpartnerpairmap.keySet())
            {
                if(orderpartnerpairmap.containsValue(id)) max = Math.max(max, ordermap.get(x).getDeliveryTime());
            }
            int hr = max/60;
            int min = max%60;
            String s2 =  ""+hr+":"+min+"";
            return s2;
        }
        public void deletePartner(String id)
        { partnermap.remove(id);
            for(String x : orderpartnerpairmap.keySet())
            {if(orderpartnerpairmap.get(x)==id) orderpartnerpairmap.remove(x);
            }
            return;
            }
        public void deleteOrder(String id)
        {ordermap.remove(id);
            for(String x : orderpartnerpairmap.keySet())
            {if(x==id) orderpartnerpairmap.remove(x);
            }
            return;
        }
}
