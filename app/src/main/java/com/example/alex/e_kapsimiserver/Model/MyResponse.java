package com.example.alex.e_kapsimiserver.Model;

import java.util.List;
import com.example.alex.e_kapsimiserver.Model.Result;
public class MyResponse {

    public long multicast_id;
    public int success;
    public int failure;
    public int canonical_ids;
    public List<Result> results;
}
