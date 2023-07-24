package com.hamza.gofood.utils;

import com.hamza.gofood.entity.Rule;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {

    private static String[] ruleNames = {"peak hour", "special days", "night charges"};
    private static int[] amount = {30, 50, 20};

    public static List<Rule> getAvailableRules() {
        List<Rule> rules = new ArrayList<>();
        for (int i = 0; i < ruleNames.length; i++) {
            rules.add(new Rule(ruleNames[i], amount[i]));
        }
        return rules;
    }
}
