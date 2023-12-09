package ru.jvn.controller;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;


@Controller
@RequestMapping(value = "/sec")
public class Sec {


    @RequestMapping(value = "/way/{str}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSec(@PathVariable String str) {

        return "Response-sec-param=" + str;

    }

//    @RequestMapping(value = "/way", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String getSec() {
//        return "Response-sec-non-param";
//    }

    @RequestMapping(value = "/way", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSecRec(@RequestParam(required = false) String view) {
        return "Response-sec-request-param  " + view;
    }
//    @RequestMapping(value = "/createRubricator", method = RequestMethod.POST, consumes = "application/json")
    @RequestMapping(value = "/way", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String getSecRecPost(@RequestBody String cvtd) {
        return "Response-sec  " + cvtd;
    }


}

