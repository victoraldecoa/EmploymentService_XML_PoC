<?xml version="1.0"?>
<applicantProfile xmlns:xsl='http://www.w3.org/1999/XSL/Transform' 
                  xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
                  xsi:schemaLocation='http://namespace.profile.com/ns/profile profile.xsd'>
    <xsl:output method="xml" indent="yes" />
	<xsl:variable name="profile" select="document('ApplicantProfile_noGPA.xml')/applicantProfile"/>
	<xsl:variable name="transcript" select="document('Transcript.xml')/transcript"/> 
    <xsl:template match="profile" >
    	<languages>
		    <xsl:for-each select="$profile/languages/language"> 
		        <language> 
					<xsl:attribute name="name">
						<xsl:value-of select="@name"/>
					</xsl:attribute>
				    <reading><xsl:value-of select="./reading"/></reading>
				    <speaking><xsl:value-of select="./speaking"/></speaking>
				    <understanding><xsl:value-of select="./understanding"/></understanding>
				    <writing><xsl:value-of select="./writing"/></writing>
		        </language>
		    </xsl:for-each>
    	</languages>
    	<competences>
		    <xsl:for-each select="$profile/competences/competence">
		    	<competence><xsl:value-of select="."/></competence>
		    </xsl:for-each>
    	</competences>
        <address><xsl:value-of select="$profile/address" /></address>
        <birth><xsl:value-of select="$profile/birth" /></birth>
        <city><xsl:value-of select="$profile/city" /></city>
        <name><xsl:value-of select="$profile/name" /></name>
        <number_worked_companies><xsl:value-of select="$profile/number_worked_companies" /></number_worked_companies>
        <state><xsl:value-of select="$profile/state" /></state>
        <gpa><xsl:value-of select="(4 * sum($transcript/courses/course[grade='A']/credits)
                                  + 3 * sum($transcript/courses/course[grade='B']/credits)
                                  + 2 * sum($transcript/courses/course[grade='C']/credits)
                                  +     sum($transcript/courses/course[grade='D']/credits))
                                                          div
                                        sum($transcript/courses/course/credits)"/></gpa>
    </xsl:template>
</applicantProfile>
