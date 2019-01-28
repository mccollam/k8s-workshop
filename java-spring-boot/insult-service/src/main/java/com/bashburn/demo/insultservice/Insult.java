package com.bashburn.demo.insultservice;

public class Insult {
  private String adjective;
  private String adjectiveTwo;
  private String noun;
  private String combined;

  public Insult(final String adjective, final String adjectiveTwo, final String noun) {
    this.adjective = adjective;
    this.adjectiveTwo = adjectiveTwo;
    this.noun = noun;
    this.combined = adjective + ", " + adjectiveTwo + " " + noun;
  }

  public String getAdjective() {
    return adjective;
  }

  public void setAdjective(final String adjective) {
    this.adjective = adjective;
  }

  public String getAdjectiveTwo() {
    return adjectiveTwo;
  }

  public void setAdjectiveTwo(final String adjectiveTwo) {
    this.adjectiveTwo = adjectiveTwo;
  }

  public String getNoun() {
    return noun;
  }

  public void setNoun(final String noun) {
    this.noun = noun;
  }

  public String getCombined() {
    return combined;
  }

  public void setCombined(final String combined) {
    this.combined = combined;
  }
}
