package io.github.ezequielb.gradle.dependencyenforcer.util

import io.github.ezequielb.gradle.dependencyenforcer.util.PatternUtils
import org.junit.Assert
import org.junit.Test

import java.util.regex.Pattern


class PatternUtilsTest {

    @Test
    public void testStaticPatterns() {
        Pattern pattern =  PatternUtils.buildPackagePattern("com.example")
        Assert.assertTrue(pattern.matcher("com.example.Class1").matches())
        Assert.assertTrue(pattern.matcher("com.example.package1.Class1").matches())
        Assert.assertTrue(pattern.matcher("com.example.package1.package2.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.package1.package2.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.examples.package2.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.exampl.package2.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.example").matches())
    }

    @Test
    public void testPatternsWith1Captures() {
        Pattern pattern =  PatternUtils.buildPackagePattern("com.{0}")
        Assert.assertTrue(pattern.matcher("com.example1.Class1").matches())
        Assert.assertTrue(pattern.matcher("com.example2.package1.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.example").matches())
        Assert.assertFalse(pattern.matcher("com1.example").matches())
        Assert.assertFalse(pattern.matcher("co.example").matches())
    }

    @Test
    public void testPatternsWith2Captures() {
        Pattern pattern =  PatternUtils.buildPackagePattern("com.{0}.x.{1}.p2")
        Assert.assertTrue(pattern.matcher("com.a.x.b.p2.Class1").matches())
        Assert.assertTrue(pattern.matcher("com.a.x.b.p2.p3.p4.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.example").matches())
        Assert.assertFalse(pattern.matcher("com1.example").matches())
        Assert.assertFalse(pattern.matcher("co.example").matches())
        Assert.assertFalse(pattern.matcher("com.a.p.b.p.p3.p4.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.p.p3.p4.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.p2").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.p").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.p2.").matches())
    }

    @Test
    public void testStrictPattern() {
        Pattern pattern =  PatternUtils.buildPackagePattern("com.x.")
        Assert.assertTrue(pattern.matcher("com.x.Class1").matches())
        Assert.assertTrue(pattern.matcher("com.x.Class2").matches())
        Assert.assertFalse(pattern.matcher("com.example").matches())
        Assert.assertFalse(pattern.matcher("com1.example").matches())
        Assert.assertFalse(pattern.matcher("com.p2.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.x.p2.Class1").matches())
    }

    @Test
    public void testGetClassPackage() {
        Assert.assertEquals("com.x",  PatternUtils.getClassPackage("com.x.Class1"))
        Assert.assertEquals("com.x.p2",  PatternUtils.getClassPackage("com.x.p2.Class1"))
        Assert.assertEquals("",  PatternUtils.getClassPackage("Class1"))
    }

    @Test
    public void testBuildWithEvaluatedCaptures() {
        Pattern pattern =  PatternUtils.buildPackagePattern("com.{0}.x.{1}.p2")
        Assert.assertTrue(pattern.matcher("com.a.x.b.p2.Class1").matches())
        Assert.assertTrue(pattern.matcher("com.a.x.b.p2.p3.p4.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.example").matches())
        Assert.assertFalse(pattern.matcher("com1.example").matches())
        Assert.assertFalse(pattern.matcher("co.example").matches())
        Assert.assertFalse(pattern.matcher("com.a.p.b.p.p3.p4.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.p.p3.p4.Class1").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.p2").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.p").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.").matches())
        Assert.assertFalse(pattern.matcher("com.a.x.b.p2.").matches())
    }

}
