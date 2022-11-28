package models;

public class Member
{
    private Hero hero;
    private Organization organization;

    public Hero getHero()
    {
        return hero;
    }

    public void setHero(Hero hero)
    {
        this.hero = hero;
    }

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hero == null) ? 0 : hero.hashCode());
        result = prime * result + ((organization == null) ? 0 : organization.hashCode());
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
        if (hero == null)
        {
            if (other.hero != null)
                return false;
        }
        else if (!hero.equals(other.hero))
            return false;
        if (organization == null)
        {
            if (other.organization != null)
                return false;
        }
        else if (!organization.equals(other.organization))
            return false;
        return true;
    }
}
