package models;

public class Member
{
    private int heroID;
    private int organizationID;

    public int getHeroID()
    {
        return heroID;
    }

    public void setHeroID(int heroID)
    {
        this.heroID = heroID;
    }

    public int getOrganizationID()
    {
        return organizationID;
    }

    public void setOrganizationID(int organizationID)
    {
        this.organizationID = organizationID;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + heroID;
        result = prime * result + organizationID;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Member other = (Member) obj;
        if (heroID != other.heroID)
            return false;
        if (organizationID != other.organizationID)
            return false;
        return true;
    }
}
