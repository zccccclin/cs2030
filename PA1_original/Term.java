interface Term {
    public String getId();

    public boolean equals(Term t);

    public Term addInfo(ImList<String> infoChain);
}
