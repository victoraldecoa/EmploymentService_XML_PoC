<?xml version="1.0"?>
<applicantProfile xmlns:xsl='http://www.w3.org/1999/XSL/Transform' 
                  xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
                  xsi:schemaLocation='http://namespace.profile.com/ns/profile profile.xsd'>
    <xsl:output method="xml" />
	<xsl:variable name="profile" select="document('ApplicantProfile_noGPA.xml')/applicantProfile"/>
	<xsl:variable name="transcript" select="document('Transcript.xml')/transcript"/> 
    <xsl:template match="profile" >
        <name><xsl:value-of select="$profile/name" /></name>
        <address><xsl:value-of select="$profile/address" /></address>
        <gpa><xsl:value-of select="(4 * sum($transcript/courses/course[grade='A']/credits)
                                 + 3 * sum($transcript/courses/course[grade='B']/credits)
                                 + 2 * sum($transcript/courses/course[grade='C']/credits)
                                 +     sum($transcript/courses/course[grade='D']/credits))
                                                         div
                                       sum($transcript/courses/course/credits)"/></gpa>
    </xsl:template>
</applicantProfile>        	
