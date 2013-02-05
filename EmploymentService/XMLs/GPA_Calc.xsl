<?xml version="1.0"?>
<app:profile xmlns:xsl='http://www.w3.org/1999/XSL/Transform' 
                  xmlns:apcv='http://namespace.profile.com/ns/cv'
                  xmlns:com='http://namespace.profile.com/ns/com'
                  xmlns:t='http://namespace.profile.com/ns/transcript'
                  xmlns:e='http://namespace.profile.com/ns/empRecord'
                  xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
                  xsi:schemaLocation='http://namespace.profile.com/ns/profile profile.xsd'
                  xmlns:app='http://namespace.profile.com/ns/profile'>
                      
	<xsl:output method="xml" />
	<xsl:variable name="profile" select="document('ApplicantProfile_noGPA.xml')/prof:profile"/>
	<xsl:variable name="transcript" select="document('Transcript.xml')/t:transcript"/> 
    <xsl:template match="prof:profile" >
            
                <app:cv_info>
		            <app:name><xsl:value-of select="$cv/apcv:name" /> </app:name>
		            <app:address><xsl:value-of select="$cv/apcv:address" /> </app:address>
		            <app:telephone><xsl:value-of select="$cv/apcv:telephone" /> </app:telephone>
		            <app:email><xsl:value-of select="$cv/apcv:email" /></app:email>
		            <app:education><xsl:value-of select="$cv/apcv:education" /> </app:education>
		            <xsl:for-each select="$cv/apcv:language"> 
		                    <app:language> <xsl:value-of select="." /></app:language>
		            </xsl:for-each>
		            <app:qualifications><xsl:value-of select="$cv/apcv:qualifications" /></app:qualifications>
		            <app:jobType><xsl:value-of select="$cv/apcv:jobType" /></app:jobType>
		            <app:desiredPosition><xsl:value-of select="$cv/apcv:desiredPosition" /></app:desiredPosition>
		            <app:references><xsl:value-of select="$cv/apcv:references" /> </app:references>
		            <app:motivation><xsl:value-of select="$cv/apcv:motivation" /> </app:motivation>
                </app:cv_info>
                
                <app:empRecord>
                <app:empName><xsl:value-of select="$empRecord/e:empName"/></app:empName>
                <xsl:for-each select="$empRecord/e:companyWorked">
                <app:companyWorked>  
                    <app:workedAtComId><xsl:value-of select="./e:workedAtComId"/></app:workedAtComId>
                    <app:workedAt><xsl:value-of select="./e:workedAt"/></app:workedAt>  
                    <app:duration><xsl:value-of select="$empRecord/e:companyWorked/e:duration"/></app:duration>
                    <app:year><xsl:value-of select="$empRecord/e:companyWorked/e:year"/></app:year>
                     <app:position><xsl:value-of select="$empRecord/e:companyWorked/e:position"/></app:position>
                </app:companyWorked>
                </xsl:for-each>
               </app:empRecord>
            <app:companyInfo>
                
                <app:companies>
                <xsl:for-each select="$companyInfo/*/com:companyId">
                <xsl:choose>
                <xsl:when test=". = $companyWorked">
                <app:company> 
                   <app:companyId><xsl:value-of select="../com:companyId"/></app:companyId>
                   <app:companyName><xsl:value-of select="../com:companyName"/></app:companyName>
                   <app:category><xsl:value-of select="../com:category"/></app:category>
                   <app:founders><xsl:value-of select="../com:founders"/></app:founders>
                   <app:ceo><xsl:value-of select="../com:ceo"/></app:ceo>
                   <app:location><xsl:value-of select="../com:location"/></app:location>
                   <app:contact><xsl:value-of select="../com:contact"/></app:contact>
                </app:company> 
                </xsl:when>
                <xsl:otherwise />
                </xsl:choose>    
                </xsl:for-each> 
            </app:companies>
            </app:companyInfo>

            <app:transcript>

            <app:studentName><xsl:value-of select="$transcript/t:studentName"/></app:studentName>
            <app:university><xsl:value-of select="$transcript/t:university"/></app:university>
            <app:program><xsl:value-of select="$transcript/t:program"/></app:program> 
            <app:issueDate><xsl:value-of select="$transcript/t:issueDate"/></app:issueDate>
            <!-- <xsl:value-of select=" (4*(sum($transcript/courses/course[grade='A']/credits)) + 3*(sum($transcript/courses/course[grade='B']/credits)) + 2*(sum($transcript/courses/course[grade='C']/credits)) + 1*(sum($transcript/courses/course[grade='D']/credits))) div (sum($transcript/courses/course/credits))"/>  -->
            <app:gpa><xsl:value-of select="format-number( (4*(sum($transcript/t:courses/t:course[t:grade='A']/t:credits)) + 3*(sum($transcript/t:courses/t:course[t:grade='B']/t:credits)) + 2*(sum($transcript/t:courses/t:course[t:grade='C']/t:credits)) + 1*(sum($transcript/t:courses/t:course[t:grade='D']/t:credits))) div (sum($transcript/t:courses/t:course/t:credits)), '#.000')"/>
            </app:gpa>
            <app:courses>
            <xsl:for-each select="$transcript/t:courses/t:course">
                <app:course>
                    <app:code><xsl:value-of select="./t:code"/></app:code>
                    <app:title><xsl:value-of select="./t:title"/></app:title>
                    <app:credits><xsl:value-of select="./t:credits"/></app:credits>
                    <app:grade><xsl:value-of select="./t:grade"/></app:grade>
                </app:course>
            </xsl:for-each>
            </app:courses>
            </app:transcript> 
                
        
    </xsl:template>
</app:profile>        	
